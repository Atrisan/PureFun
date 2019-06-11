package printer;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTDefinition;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun.printer.TypesPrinter;
import lang.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class TypesPrinterTest extends AbstractTest {

    private static final String[] EXPECTED_TYPES = { "Int", "Int8", "Int16", "Int32", "Int64", "Long", "UInt", "UInt8",
            "UInt16", "UInt32", "UInt64", "Double", "Float", "Float64", "Char",
            "String", "Boolean", "[Int]", "[String]", "(Int, String)", "(Double, Int64, Boolean)",
            "[Int, Char]", "(age: Int, name: String)"};

    @Test
    public void testTypesPrinter() {
        ASTModule module = parseModel("./src/test/resources/parser/Valid/GlobalVariables.pf");

        for (int i = 0; i < module.getDefinitionList().size(); i++) {
            ASTDefinition def = module.getDefinition(i);
            if (def instanceof ASTVariable) {
                ASTVariable var = (ASTVariable) def;
                Assert.assertEquals(EXPECTED_TYPES[i], TypesPrinter.printType(var.getType()));
                Log.info(TypesPrinter.printType(var.getType()), "Type of " + (var.getName()));
            }
        }
    }
}
