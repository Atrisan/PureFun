package symboltable;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Symbol;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class SymbolTableCreatorTest {

    public static final String MODEL_SOURCE_PATH = "./src/test/resources/symboltable/";

    @ParameterizedTest
    @CsvSource(
            "Simple"
    )
    public void test(String modelPath) {
        ModelPath path = new ModelPath(Paths.get(MODEL_SOURCE_PATH));

        GlobalScope globalScope = PureFunScopeCreator.createGlobalScope(path);
        Optional<ModuleSymbol> moduleSymbol = globalScope.resolve(modelPath, ModuleSymbol.KIND);

        Assertions.assertTrue(moduleSymbol.isPresent());

        ASTModule module = moduleSymbol.get().getModuleNode().get();

        ASTVariable var = ((ASTVariable)module.getDefinition(0));

        Log.info(var.getName(), "Variable definition in " + module.getName());

        Optional<VariableSymbol> varSymbol = moduleSymbol.get().getSpannedScope().resolve(var.getName(), VariableSymbol.KIND);

        Assertions.assertTrue(varSymbol.isPresent());
        Log.info(String.valueOf(varSymbol.isPresent()), "varSymbol present:");

        Assertions.assertTrue(varSymbol.get().getType().equals("Int"));
        Log.info(varSymbol.get().getType(), "variable " + varSymbol.get().getName() + " type:");
    }
}
