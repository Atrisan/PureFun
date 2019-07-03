package types;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTDefinition;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun.printer.PureFunExpressionPrinter;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.Optional;

public class ExpressionTypesResolverTest {

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
    }

    @ParameterizedTest
    @CsvSource(
        "./src/test/resources/parser/Valid/"
    )
    public void test(String modelPath) {
        GlobalScope globalScope = PureFunScopeCreator.createGlobalScope(new ModelPath(Paths.get(modelPath)));
        Optional<ModuleSymbol> moduleSymbol = globalScope.resolve("GlobalVariables", ModuleSymbol.KIND);
        Assertions.assertTrue(moduleSymbol.isPresent());
        Assertions.assertTrue(moduleSymbol.get().getModuleNode().isPresent());

        ASTModule ast = moduleSymbol.get().getModuleNode().get();

        for (ASTDefinition def : ast.getDefinitionList()) {
            if (def instanceof ASTVariable) {
                ASTVariable var = (ASTVariable) def;

                if (var.isPresentExpression()) {
                    Optional<PureFunType> resolvedType = ExpressionTypesResolver.resolveType(var.getExpression());
                    String expressionStr = PureFunExpressionPrinter.printExpression(var.getExpression());

                    if (resolvedType.isPresent()) {
                        Log.info(resolvedType.get().getTypeString(), expressionStr + " type:");
                    } else {
                        Log.error(expressionStr + ": Could not resolve type");
                    }

                }
            }
        }
    }
}
