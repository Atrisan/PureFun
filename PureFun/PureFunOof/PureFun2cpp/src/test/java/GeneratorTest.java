
import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Optional;

public class GeneratorTest extends AbstractTest {
    public static final String MODEL_SOURCE_PATH = "./src/test/resources/generator/";

    @Test
    public void testGeneratorModuleWithDataStructures() {
        ModelPath modelPath = new ModelPath(Paths.get(MODEL_SOURCE_PATH));
        GlobalScope symbolTable = PureFunScopeCreator.createGlobalScope(modelPath);
        Optional<ModuleSymbol> moduleSymbol = symbolTable.resolve("TestDF", ModuleSymbol.KIND);
        Assert.assertTrue(moduleSymbol.isPresent());
        Assert.assertTrue(moduleSymbol.get().getModuleNode().isPresent());
        ASTModule ast = moduleSymbol.get().getModuleNode().get();
        PureFunGenerator gen = new PureFunGenerator();
        gen.generate(ast, symbolTable,"ModuleWithDestructure.cxx");
    }
}