//> Representing Code ast-printer
package com.shynebulas.jlox;
//> omit

import java.util.List;
//< omit

/* Representing Code ast-printer < Statements and State omit
class AstPrinter implements Expr.Visitor<String> {
*/
//> Statements and State omit
class AstPrinter implements Expr.Visitor<String> {
    //< Statements and State omit
    String print(Expr expr) {
        return expr.accept(this);
    }
//> Statements and State omit


    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }
//> Functions omit


//< Classes omit

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }
//> Control Flow omit


//< Control Flow omit
//> Classes omit


//< Classes omit
//> Inheritance omit


//< Inheritance omit
//> Classes omit


//< Classes omit

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }
//> Statements and State omit


    //< Statements and State omit
//< visit-methods
//> print-utilities
    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
    //< print-utilities
//> omit
    // Note: AstPrinting other types of syntax trees is not shown in the
    // book, but this is provided here as a reference for those reading
    // the full code.




  public static void main(String[] args) {
    Expr expression = new Expr.Binary(
        new Expr.Unary(
            new Token(TokenType.MINUS, "-", null, 1),
            new Expr.Literal(123)),
        new Token(TokenType.STAR, "*", null, 1),
        new Expr.Grouping(
            new Expr.Literal(45.67)));

    System.out.println(new AstPrinter().print(expression));
  }

}