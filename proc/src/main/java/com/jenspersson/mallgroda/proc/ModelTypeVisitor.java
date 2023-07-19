package com.jenspersson.mallgroda.proc;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;
import javax.lang.model.element.Element;

public class ModelTypeVisitor implements TypeVisitor<Void, TypeInfo> {
        
    @Override
    public Void visit(TypeMirror t, TypeInfo p) {
        return null;
    }

    @Override
    public Void visit(TypeMirror t) {
        return null;
    }

    @Override
    public Void visitArray(ArrayType t, TypeInfo p) {
        return null;
    }

    @Override
    public Void visitExecutable(ExecutableType t, TypeInfo p) {
        return null;
    }

    @Override
    public Void visitDeclared(DeclaredType t, TypeInfo p) {
        return null;
    }
    
    @Override
    public Void visitError(ErrorType t, TypeInfo p) {
        return null;
    }
    @Override
    public Void visitIntersection(IntersectionType t, TypeInfo p) {
        return null;
    }
            
    @Override
    public Void visitNoType(NoType t, TypeInfo p) {
        return null;
    }

    @Override
    public Void visitNull(NullType t, TypeInfo p) {
        return null;
    }
            
    @Override
    public Void visitPrimitive(PrimitiveType t, TypeInfo p) {
        return null;
    }
            
    @Override
    public Void visitTypeVariable(TypeVariable t, TypeInfo p) {
        return null;
    }
            
    @Override
    public Void visitUnion(UnionType t, TypeInfo p) {
        return null;
    }
            
    @Override
    public Void visitUnknown(TypeMirror t, TypeInfo p) {
        return null;
    }

    @Override
    public Void visitWildcard(WildcardType t, TypeInfo p) {
        return null;
    }
}