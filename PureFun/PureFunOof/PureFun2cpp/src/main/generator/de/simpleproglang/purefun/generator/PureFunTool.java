package de.simpleproglang.purefun.generator;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun.coco.*;
import de.simpleproglang.purefun.coco.typechecking.AssignmentExpressionTypeCheckCoCo;
import de.simpleproglang.purefun.coco.typechecking.CommonForControlTypeCheckCoCo;
import de.simpleproglang.purefun.coco.typechecking.IfTypeCheckCoCo;
import de.simpleproglang.purefun.coco.typechecking.VariableAssignmentTypeCheckCoCo;

import java.nio.file.Paths;
import java.util.Optional;

public class PureFunTool {

    public static void main(String[] args) {
        String sourceDirectory = args[1];
        String sourceName = args[2];

        ModelPath modelPath = new ModelPath(Paths.get(sourceDirectory));
        GlobalScope symbolTable = PureFunScopeCreator.createGlobalScope(modelPath);
        Optional<ModuleSymbol> moduleSymbol = symbolTable.resolve(sourceName, ModuleSymbol.KIND);

        if (moduleSymbol.isPresent()) {
            Log.error("Module needs to be named after file!");
        }
        if (moduleSymbol.get().getModuleNode().isPresent()) {
            Log.error("Module not present!");
        }

        ASTModule ast = moduleSymbol.get().getModuleNode().get();

        Log.info("module loaded", "FunctionExistsCoco");

        PureFunCoCoChecker checker = new PureFunCoCoChecker();

        //TODO: extend type checking CoCos

        AssignmentExpressionTypeCheckCoCo CoCoAssignmentExpressionTypeCheckCoCo = new AssignmentExpressionTypeCheckCoCo();
        checker.addCoCo(CoCoAssignmentExpressionTypeCheckCoCo);

        CommonForControlTypeCheckCoCo CoCoCommonForControlTypeCheckCoCo = new CommonForControlTypeCheckCoCo();
        checker.addCoCo(CoCoCommonForControlTypeCheckCoCo);

        IfTypeCheckCoCo CoCoIfTypeCheckCoCo = new IfTypeCheckCoCo();
        checker.addCoCo(CoCoIfTypeCheckCoCo);

        VariableAssignmentTypeCheckCoCo variableAssignmentTypeCheckCoCo = new VariableAssignmentTypeCheckCoCo(moduleSymbol.get());
        checker.addCoCo(variableAssignmentTypeCheckCoCo);


        // NON-typechecking CoCos

        AsyncFunctionExistsCoCo CoCoAsyncFunctionExistsCoCo = new AsyncFunctionExistsCoCo();
        checker.addCoCo(CoCoAsyncFunctionExistsCoCo);

        ContainerExistsIndexAccessCoCo CoCoContainerExistsIndexAccessCoCo = new ContainerExistsIndexAccessCoCo();
        checker.addCoCo(CoCoContainerExistsIndexAccessCoCo);

        ContainerExistsLengthCoCo CoCoContainerExistsLengthCoCo = new ContainerExistsLengthCoCo();
        checker.addCoCo(CoCoContainerExistsLengthCoCo);

        DataStructureSubVariableExistsCoCo CoCoDataStructureSubVariableExistsCoCo = new DataStructureSubVariableExistsCoCo();
        checker.addCoCo(CoCoDataStructureSubVariableExistsCoCo);

        FunctionDefinedMultipleTimesCoCo CoCoFunctionDefinedMultipleTimesCoCo = new FunctionDefinedMultipleTimesCoCo();
        checker.addCoCo(CoCoFunctionDefinedMultipleTimesCoCo);

        FunctionExistsCoCo CoCoFunctionExistsCoCo = new FunctionExistsCoCo();
        checker.addCoCo(CoCoFunctionExistsCoCo);

        ImmutableGlobalVariableCoCo CoCoImmutableGlobalVariableCoCo = new ImmutableGlobalVariableCoCo();
        checker.addCoCo(CoCoImmutableGlobalVariableCoCo);

        MapExistsKeyListCoCo CoCoMapExistsKeyListCoCo = new MapExistsKeyListCoCo();
        checker.addCoCo(CoCoMapExistsKeyListCoCo);

        MapExistsValueListCoCo CoCoMapExistsValueListCoCo = new MapExistsValueListCoCo();
        checker.addCoCo(CoCoMapExistsValueListCoCo);

        MapOrListExistsConcatCoCo CoCoMapOrListExistsConcatCoCo = new MapOrListExistsConcatCoCo();
        checker.addCoCo(CoCoMapOrListExistsConcatCoCo);

        ReturnExpressionNecessaryCoCo CoCoReturnExpressionNecessaryCoCo = new ReturnExpressionNecessaryCoCo();
        checker.addCoCo(CoCoReturnExpressionNecessaryCoCo);

        UniqueDataStructureNameCoCo CoCoUniqueDataStructureNameCoCo = new UniqueDataStructureNameCoCo();
        checker.addCoCo(CoCoUniqueDataStructureNameCoCo);

        VariableExistsCoCo CoCoVariableExistsCoCo = new VariableExistsCoCo();
        checker.addCoCo(CoCoVariableExistsCoCo);

        VariableTypeDefinedCoCo CoCoVariableTypeDefinedCoCo = new VariableTypeDefinedCoCo();
        checker.addCoCo(CoCoVariableTypeDefinedCoCo);

        checker.checkAll(ast);


        PureFunGenerator gen = new PureFunGenerator();
        gen.generate(ast, symbolTable,sourceName + ".cxx");
    }
}
