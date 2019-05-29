package ad2java.generator;

import activitydiagram._ast.*;
import activitydiagram._symboltable.ActivityDiagramSymbol;
import activitydiagram._symboltable.JunctionSymbol;
import activitydiagram._symboltable.Type;
import activitydiagram._symboltable.VariableSymbol;
import de.monticore.generating.GeneratorEngine;
import de.monticore.generating.GeneratorSetup;
import de.monticore.oclexpressions._ast.ASTEDeclarationExt;
import de.monticore.symboltable.Symbol;
import de.monticore.types.types._ast.ASTReferenceType;
import de.monticore.types.types._ast.ASTSimpleReferenceType;
import de.se_rwth.commons.logging.Log;

import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ActivityGenerator {

    public Optional<ASTEdge> getStart(List<ASTEdge> edges) {
        // start element is always there by specification
        for (ASTEdge edge : edges) {
            if (edge.isStart()) {
                return Optional.of(edge);
            }
        }

        return Optional.empty();
    }

    public ASTElement getElementFromName(String name, ASTActivityDiagram ast) {
        return ast.getElementList().stream().filter(x -> x.getName().equals(name)).findAny().get();
    }

    public List<ASTEdge> filterEdges(Predicate<ASTEdge> pred, List<ASTEdge> edges) {
        return edges.stream().filter(pred).collect(Collectors.toList());
    }

    public List<ASTElement> topologicalSort(List<ASTEdge> edges, ASTActivityDiagram ast) {
        List<ASTElement> result = new ArrayList<>();
        List<ASTElement> working_set = new ArrayList<>();

        ASTEdge startEdge = this.getStart(edges).get();
        working_set.add(this.getElementFromName(startEdge.getTo(), ast));

        List<ASTEdge> astEdges = new ArrayList<>();
        astEdges.addAll(edges);
        astEdges.remove(startEdge);
        astEdges = astEdges.stream().filter(x -> !x.isEnd()).collect(Collectors.toList());

        while (!working_set.isEmpty()) {
            ASTElement current = working_set.remove(0);
            result.add(current);
            List<ASTEdge> edgeList = filterEdges(x -> x.isPresentFrom() && x.getFrom().equals(current.getName()), astEdges);
            for(ASTEdge edge : edgeList) {
                ASTElement m = this.getElementFromName(edge.getTo(), ast);
                astEdges.remove(edge);
                List<ASTEdge> incomingM = filterEdges(x -> x.isPresentTo() && x.getTo().equals(m.getName()), astEdges);
                if(incomingM.isEmpty()) {
                    working_set.add(m);
                }
            }
        }

        return result;
    }

    public void generate(ActivityDiagramSymbol symbol) {
        GeneratorSetup gs = new GeneratorSetup();
        gs.setCommentStart("/*");
        gs.setCommentEnd("*/");

        GeneratorEngine ge = new GeneratorEngine(gs);
        ASTActivityDiagram ast = symbol.getActivityDiagramNode().get();
        Map<String, String> typeMap = new HashMap<>();
        List<ASTActivity> activities = new ArrayList<>();
        List<ASTEdge> edges = new ArrayList<>();
        edges.addAll(ast.getEdgeList());
        List<ASTElement> sortedNodes = topologicalSort(edges, ast);

        for (ASTVariable variable : ast.getVariableList()) {
            typeMap.put(variable.getName(), ((ASTSimpleReferenceType) variable.getType()).getName(0));
        }

        Map<String, Type> junctionTypeMap = new HashMap<>();

        for (ASTElement el : ast.getElementList()) {
            if (el instanceof ASTActivity) {
                ASTActivity activityAst = (ASTActivity) el;
                activities.add(activityAst);

                if (activityAst.isPresentAction()) {
                    Log.info("generate " + activityAst.getName() + " activity with template", "Generator");
                    Log.info(((ASTSimpleReferenceType) ast.getVariable(0).getType()).getName(0), "type:");

                    ge.generate("template/ActivityTemplate.ftl", Paths.get(activityAst.getName() + "Activity.java"), activityAst, typeMap);
                }
            } else if (el instanceof ASTJunction) {
                ASTJunction junction = (ASTJunction) el;
                junctionTypeMap.put(junction.getName(), ((JunctionSymbol) junction.getSymbolOpt().get()).getType());
            }
        }

        Log.info(String.valueOf(junctionTypeMap.isEmpty()), "isEmpty:");

        ge.generate("template/ActivityDiagramTemplate.ftl", Paths.get(ast.getName() + "ActivityDiagram.java"), ast, activities, sortedNodes, junctionTypeMap);
    }

}
