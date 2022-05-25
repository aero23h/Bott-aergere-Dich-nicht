
public class Score {

	private byte[] startBoard;
	private byte[] goalBoard;
	private byte[] onBoard;
	
	public Score() {
		this.init();
		
	}
	
	public void init() {
		// TokenNumber AB-A is Player, B is TokenNumber eg 13 - player B token 3
		this.startBoard = new byte[] {01,02,03,04,11,12,13,14,21,22,23,24,31,32,33,34};
		this.goalBoard = new byte[16];
		this.onBoard = new byte[40];
	}
	
	public boolean move(byte token, byte steps) {
		// return true everything OK, false = something wrong
		// find token in onBoard
		int tokenPos = -1;
		for(int i=0;i < this.onBoard.length; i++) {
			if(this.onBoard[i] == token) {
				tokenPos = i;
				break;
			}
		}
		if(tokenPos > -1) {
			// move token x steps
			return true;
		}
		// if not found, find token in offBoard
		for(int i=0;i < this.goalBoard.length; i++) {
			if(this.goalBoard[i] == token) {
				tokenPos = i;
				break;
			}
		}
		if(tokenPos > -1) {
			// move token in goalBoard
			return true;
		}
		// draw start token
		if(steps == 6) {
			// get player from token AB --> A
			int player = (token / 10);
			for(int i=0; i<4;i++) {
				// counter i + playerNumber * 4
				if(this.startBoard[i+player*4] == token) {
					// draw token to start
					return true;
				}
			}
		}
		return false;
	}

	public byte[] getStartBoard() {
		return startBoard;
	}

	public void setStartBoard(byte[] startBoard) {
		this.startBoard = startBoard;
	}

	public byte[] getGoalBoard() {
		return goalBoard;
	}

	public void setGoalBoard(byte[] goalBoard) {
		this.goalBoard = goalBoard;
	}

	public byte[] getOnBoard() {
		return onBoard;
	}

	public void setOnBoard(byte[] onBoard) {
		this.onBoard = onBoard;
	}
}
