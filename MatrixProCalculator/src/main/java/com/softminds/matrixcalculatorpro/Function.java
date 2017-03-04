/*
 * Copyright (C) 2017 Ashar Khan <ashar786khan@gmail.com>
 *
 * This file is part of Matrix Calculator.
 *
 * Matrix Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matrix Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matrix Calculator.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.softminds.matrixcalculatorpro;


import java.util.ArrayList;

public class Function {
    private Matrix ConstantMatrix;
    private ArrayList<Terms> terms;
    private static class Terms{
        private int Exponent;
        private boolean Sign; //true  is Positive else False is assigned;
        private float Coefficiet;
        private Terms (float coefficiet,int exponent,boolean sign) {
            Exponent = exponent;
            Sign = sign;
            Coefficiet = coefficiet;
        }
        private int getExponent(){
            return Exponent;
        }
        private boolean getSign(){
            return Sign;
        }
        private float getCoefficiet() {
            return Coefficiet;
        }
    }
    public Function(int R,int C,float Const){
        ConstantMatrix = new Matrix(R,C,Type.Normal);
        ConstantMatrix.MakeIdentity();
        ConstantMatrix.ScalarMultiply(Const);
         terms = new ArrayList<>();
    }
    public void AddTerms(float Coe,int Expo,boolean sign){
        Terms t = new Terms(Coe,Expo,sign);
        terms.add(t);
    }
    public Matrix ComputeFunction(Matrix operand){
        Matrix OriginalCopy = new Matrix(operand.GetRow(),operand.GetCol(),operand.GetType());
        OriginalCopy.CloneFrom(operand);
        Matrix Resultant = new Matrix(operand.GetRow(),operand.GetCol(),operand.GetType());
        Resultant.MakeNull();
        for(int i = 0; i<terms.size();i++)
        {
            operand.CloneFrom(OriginalCopy);
            int expo = terms.get(i).getExponent();
            boolean s = terms.get(i).getSign();
            operand.Raiseto(expo);
            if(s)
                  operand.ScalarMultiply(1*terms.get(i).getCoefficiet());
            else
                  operand.ScalarMultiply(-1*terms.get(i).getCoefficiet());
            Resultant.AddtoThis(operand);
        }
        Resultant.AddtoThis(ConstantMatrix);
        return Resultant;
    }
    @Override
    public String toString(){
        String s="";
        for(int i=0;i<terms.size();i++){
           s+= Sign(terms.get(i).getSign());
            s+= String.valueOf(terms.get(i).getCoefficiet());
            s+= String.valueOf(terms.get(i).getExponent());
            s+=" ";
        }
        return s;
    }
    private String Sign(boolean b){
      if(b)
          return "+";
        else
          return "-";
    }




}
