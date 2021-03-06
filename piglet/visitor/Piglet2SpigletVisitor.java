//
// Generated by JTB 1.3.2
//

package piglet.visitor;
import java.util.Enumeration;

import piglet.syntaxtree.BinOp;
import piglet.syntaxtree.CJumpStmt;
import piglet.syntaxtree.Call;
import piglet.syntaxtree.ErrorStmt;
import piglet.syntaxtree.Exp;
import piglet.syntaxtree.Goal;
import piglet.syntaxtree.HAllocate;
import piglet.syntaxtree.HLoadStmt;
import piglet.syntaxtree.HStoreStmt;
import piglet.syntaxtree.IntegerLiteral;
import piglet.syntaxtree.JumpStmt;
import piglet.syntaxtree.Label;
import piglet.syntaxtree.MoveStmt;
import piglet.syntaxtree.NoOpStmt;
import piglet.syntaxtree.Node;
import piglet.syntaxtree.NodeList;
import piglet.syntaxtree.NodeListOptional;
import piglet.syntaxtree.NodeOptional;
import piglet.syntaxtree.NodeSequence;
import piglet.syntaxtree.NodeToken;
import piglet.syntaxtree.Operator;
import piglet.syntaxtree.PrintStmt;
import piglet.syntaxtree.Procedure;
import piglet.syntaxtree.Stmt;
import piglet.syntaxtree.StmtExp;
import piglet.syntaxtree.StmtList;
import piglet.syntaxtree.Temp;
import piglet.piglet2spiglet.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class Piglet2SpigletVisitor implements GJVisitor<String ,Environment> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public String visit(NodeList n, Environment argu) {
      String _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public String visit(NodeListOptional n, Environment argu) {
      if ( n.present() ) {
         String _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public String visit(NodeOptional n, Environment argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public String visit(NodeSequence n, Environment argu) {
      String _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public String visit(NodeToken n, Environment argu) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> "MAIN"
    * f1 -> StmtList()
    * f2 -> "END"
    * f3 -> ( Procedure() )*
    * f4 -> <EOF>
    */
   public String visit(Goal n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "Goal";
      
      argu.print("MAIN");
      ++argu.tab_num;
      n.f1.accept(this, argu);
      --argu.tab_num;
      argu.print("END");
      n.f3.accept(this, argu);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> ( ( Label() )? Stmt() )*
    */
   public String visit(StmtList n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "StmtList";
      
      n.f0.accept(this, argu);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> Label()
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> StmtExp()
    */
   public String visit(Procedure n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "Procedure";
      
      argu.print(n.f0.f0.tokenImage+" [ "+n.f2.f0.tokenImage+" ]");
      n.f4.accept(this, argu);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    */
   public String visit(Stmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "Stmt";
      
      n.f0.accept(this, argu);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "NOOP"
    */
   public String visit(NoOpStmt n, Environment argu) {
      String _ret=null;
      argu.print("NOOP");
      return _ret;
   }

   /**
    * f0 -> "ERROR"
    */
   public String visit(ErrorStmt n, Environment argu) {
      String _ret=null;
      argu.print("ERROR");
      return _ret;
   }

   /**
    * f0 -> "CJUMP"
    * f1 -> Exp()
    * f2 -> Label()
    */
   public String visit(CJumpStmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "CJumpStmt";
      
      String exp = n.f1.accept(this, argu);
      argu.print("CJUMP "+exp+" "+n.f2.f0.tokenImage);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
   public String visit(JumpStmt n, Environment argu) {
      String _ret=null;
      argu.print("JUMP "+n.f1.f0.tokenImage);
      return _ret;
   }

   /**
    * f0 -> "HSTORE"
    * f1 -> Exp()
    * f2 -> IntegerLiteral()
    * f3 -> Exp()
    */
   public String visit(HStoreStmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "HStoreStmt";
      
      String exp1 = n.f1.accept(this, argu);
      String exp2 = n.f3.accept(this, argu);
      argu.print("HSTORE "+exp1+" "+n.f2.f0.tokenImage+" "+exp2);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "HLOAD"
    * f1 -> Temp()
    * f2 -> Exp()
    * f3 -> IntegerLiteral()
    */
   public String visit(HLoadStmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "HLoadStmt";
      
      String exp1 = n.f1.accept(this, argu);
      String exp2 = n.f2.accept(this, argu);
      argu.print("HLOAD "+exp1+" "+exp2+" "+n.f3.f0.tokenImage);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "MOVE"
    * f1 -> Temp()
    * f2 -> Exp()
    */
   public String visit(MoveStmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "MoveStmt";
      
      String exp1 = n.f1.accept(this, argu);
      String exp2 = n.f2.accept(this, argu);
      argu.print("MOVE "+exp1+" "+exp2);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "PRINT"
    * f1 -> Exp()
    */
   public String visit(PrintStmt n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "PrintStmt";
      
      String exp = n.f1.accept(this, argu);
      argu.print("PRINT "+exp);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> StmtExp()
    *       | Call()
    *       | HAllocate()
    *       | BinOp()
    *       | Temp()
    *       | IntegerLiteral()
    *       | Label()
    */
   public String visit(Exp n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "Exp";
      
      _ret = n.f0.accept(this, argu);
      if (sss.equals("CallList"))
    	  argu.now = argu.now+" "+_ret;
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "BEGIN"
    * f1 -> StmtList()
    * f2 -> "RETURN"
    * f3 -> Exp()
    * f4 -> "END"
    */
   public String visit(StmtExp n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "StmtExp";
      
      if (sss.equals("Procedure")) {
    	  argu.print("BEGIN");
    	  ++argu.tab_num;
      }
      n.f1.accept(this, argu);
      _ret = n.f3.accept(this, argu);
      if (sss.equals("Procedure")) {
    	  argu.print("RETURN "+_ret);
    	  --argu.tab_num;
    	  argu.print("END");
      }
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "CALL"
    * f1 -> Exp()
    * f2 -> "("
    * f3 -> ( Exp() )*
    * f4 -> ")"
    */
   public String visit(Call n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      
      argu.last = "Call";
      String exp = n.f1.accept(this, argu);
      
      argu.last = "CallList";
      String old = argu.now;
      argu.now = "";
      n.f3.accept(this, argu);
      _ret = "TEMP "+(++argu.max_temp);
      argu.print("MOVE "+_ret+" CALL "+exp+" ("+argu.now+" )");
      argu.now = old;
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "HALLOCATE"
    * f1 -> Exp()
    */
   public String visit(HAllocate n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "HAllocate";
      
      String exp = n.f1.accept(this, argu);
      _ret = "TEMP "+(++argu.max_temp);
      argu.print("MOVE "+_ret+" HALLOCATE "+exp);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> Operator()
    * f1 -> Exp()
    * f2 -> Exp()
    */
   public String visit(BinOp n, Environment argu) {
      String _ret=null;
      String sss = argu.last;
      argu.last = "BinOp";
      
      String op = n.f0.accept(this, argu);
      String exp1 = n.f1.accept(this, argu);
      String exp2 = n.f2.accept(this, argu);
      _ret = "TEMP "+(++argu.max_temp);
      argu.print("MOVE "+_ret+" "+op+" "+exp1+" "+exp2);
      
      argu.last = sss;
      return _ret;
   }

   /**
    * f0 -> "LT"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"
    */
   public String visit(Operator n, Environment argu) {
      String _ret=null;
      if (n.f0.which == 0)
    	  _ret = "LT";
      else if (n.f0.which == 1)
    	  _ret = "PLUS";
      else if (n.f0.which == 2)
    	  _ret = "MINUS";
      else
    	  _ret = "TIMES";
      return _ret;
   }

   /**
    * f0 -> "TEMP"
    * f1 -> IntegerLiteral()
    */
   public String visit(Temp n, Environment argu) {
      String _ret=null;
      _ret = "TEMP "+n.f1.f0.tokenImage;
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public String visit(IntegerLiteral n, Environment argu) {
      String _ret=null;
      _ret = "TEMP "+(++argu.max_temp);
      argu.print("MOVE "+_ret+" "+n.f0.tokenImage);
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public String visit(Label n, Environment argu) {
      String _ret=null;
      
      if (argu.last.equals("StmtList"))
    	  argu.print(n.f0.tokenImage+" "+"NOOP");
      else {
    	  _ret = "TEMP "+(++argu.max_temp);
    	  argu.print("MOVE "+_ret+" "+n.f0.tokenImage);
      }
      
      return _ret;
   }

}
