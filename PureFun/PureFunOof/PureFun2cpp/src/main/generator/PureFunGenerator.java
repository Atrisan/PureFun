
import de.monticore.generating.GeneratorSetup;
import de.monticore.generating.GeneratorEngine;

import java.nio.file.Paths;

import de.monticore.generating.templateengine.GlobalExtensionManagement;
import de.monticore.symboltable.GlobalScope;
import de.simpleproglang.purefun._ast.*;

public class PureFunGenerator {

    public void generate(ASTModule ast, GlobalScope symbolTable, String filename) {
        PureFunGeneratorHelper pfHelper = new PureFunGeneratorHelper(ast, symbolTable);
        GeneratorSetup gs = new GeneratorSetup();
        gs.setCommentStart("/*");
        gs.setCommentEnd("*/");
        gs.setTracing(false);
        gs.setDefaultFileExtension("cxx");

        GlobalExtensionManagement management = new GlobalExtensionManagement();
        management.setGlobalValue("pfHelper", pfHelper);

        gs.setGlex(management);

        GeneratorEngine engine = new GeneratorEngine(gs);
        engine.generate("module.ftl", Paths.get(filename), ast);


        //Generate include
        gs.setDefaultFileExtension("hxx");

        engine = new GeneratorEngine(gs);
        for (ASTDefinition definition: ast.getDefinitionList()) {
            if (definition instanceof ASTDataStructure) {
                engine.generate("definition/DataStructures.ftl", Paths.get("includes",((ASTDataStructure) definition).getName() + ".hxx"), definition, definition, ast.getName(), ast);
            }
        }



    }
}