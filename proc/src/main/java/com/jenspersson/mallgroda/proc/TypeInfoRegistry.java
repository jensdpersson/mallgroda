package com.jenspersson.mallgroda.proc;

import java.util.Map;
import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;

public class TypeInfoRegistry {
    
    private final Map<String, TypeInfo> typeInfos = new HashMap<>();
    private final AbstractTypeVisitor<MethodInfo> methodVisitor;
    private final Elements elements;
    
    TypeInfoRegistry(Elements elements) {
        this.elements = elements;
        this.methodVisitor = new AbstractTypeVisitor<MethodInfo>() {
            @Override
            public Boolean visitExecutable(ExecutableType exec, MethodInfo methodInfo) {
                if (exec.getParameterTypes().size() == 0) {
                    TypeMirror ret = exec.getReturnType();
                    ret.accept(this, methodInfo);
                    return true;
                }
                return false;
            }
            @Override
            public Boolean visitDeclared(DeclaredType decl, MethodInfo methodInfo) {
                Element elm = decl.asElement();
                methodInfo.setReturnType(elm.getSimpleName().toString());                
                List<? extends TypeMirror> paratypes = decl.getTypeArguments();
                for (TypeMirror paratype : paratypes) {
                    paratype.accept(new AbstractTypeVisitor<MethodInfo>() {
                        @Override
                        public Boolean visitDeclared(DeclaredType decl2, MethodInfo methodInfo) {
                            Element elm2 = decl2.asElement();
                            PackageElement pack = elements.getPackageOf(elm2);
                            methodInfo.setReturnTypePackage(pack.getQualifiedName().toString());
                            
                            String returnTypeName = elm2.getSimpleName().toString();
                            Element elm3 = elm2.getEnclosingElement();
                            while(elm3.asType().getKind() == TypeKind.DECLARED) {
                                returnTypeName = elm3.getSimpleName() + "." + returnTypeName;
                                elm3 = elm3.getEnclosingElement();
                            }
                            methodInfo.addReturnTypeParameter(returnTypeName);
                            
                            return true;
                        } 
                    }, methodInfo);
                }
                return true;
            }
            
        };
    }
    
    public TypeInfo get(Element elm) {
        TypeMirror typeMirror = elm.asType();
        String className = typeMirror.getClass().getName();
        TypeInfo typeInfo = typeInfos.get(className);
        //if (typeInfo == null) {
            typeInfo = new TypeInfo(elm.getSimpleName().toString());
            List<? extends Element> subs = elm.getEnclosedElements();
            for (Element sub : subs) {
                String name = sub.getSimpleName().toString();
                if (!name.equals("<init>")) {
                    MethodInfo methodInfo = new MethodInfo(name);
                    if (sub.asType().accept(methodVisitor, methodInfo)) {
                        typeInfo.addMethod(methodInfo);
                    }
                }
            }
            //id v = typeMirror.accept(modelTypeVisitor, typeInfo);
            typeInfos.put(className, typeInfo);
       // }
        return typeInfo;
    }
    
    interface Listener {
        public void onMethod(String signature);
    }
    
}