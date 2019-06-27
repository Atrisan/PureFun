
import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Optional;

public class GeneratorTest extends AbstractTest {
    public static final String MODEL_SOURCE_PATH = "./src/test/resources/generator/";

    @Test
    public void testGeneratorModuleWithDataStructures() {
        String modelname = "model4";
        ModelPath modelPath = new ModelPath(Paths.get(MODEL_SOURCE_PATH));
        GlobalScope symbolTable = PureFunScopeCreator.createGlobalScope(modelPath);
        Optional<ModuleSymbol> moduleSymbol = symbolTable.resolve(modelname, ModuleSymbol.KIND);
        Assert.assertTrue(moduleSymbol.isPresent());
        Assert.assertTrue(moduleSymbol.get().getModuleNode().isPresent());
        ASTModule ast = moduleSymbol.get().getModuleNode().get();
        Optional<VariableSymbol> var = symbolTable.resolve("model4.et", VariableSymbol.KIND);
        //Optional<VariableSymbol> var = moduleSymbol.get().getSpannedScope().resolve("et", VariableSymbol.KIND);
        PureFunGenerator gen = new PureFunGenerator();
        gen.generate(ast, symbolTable,modelname + ".cxx");
    }
}