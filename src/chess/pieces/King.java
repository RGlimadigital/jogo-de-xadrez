package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		
		this.chessMatch = chessMatch;
	}
	
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCasting(Position position) {
		
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		return piece !=null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
	}


	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		
		Position p = new Position(0,0);
		
		p.setValues(position.getRow() -1, position.getColumn());
		
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Below
		p.setValues(position.getRow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Nw
		p.setValues(position.getRow() -1 , position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Ne
		p.setValues(position.getRow() -1 , position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Sw
		p.setValues(position.getRow() + 1 , position.getColumn() - 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//Se
		p.setValues(position.getRow() + 1 , position.getColumn() + 1 );
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// #specialMove castling 
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			//King side rook
			Position pt1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCasting(pt1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			
			
			//Queen side rook
			Position pt2 = new Position(position.getRow(), position.getColumn() - 4);
			
			if(testRookCasting(pt2)) {
				Position p3 = new Position(position.getRow(), position.getColumn() - 1);
				Position p4 = new Position(position.getRow(), position.getColumn() - 2);
				Position p5 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p3) == null && getBoard().piece(p4) == null && getBoard().piece(p5) == null ) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		
		
		return mat;
	}

}
