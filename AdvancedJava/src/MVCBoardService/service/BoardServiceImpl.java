package MVCBoardService.service;

import java.util.List;

import MVCBoardService.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	@Override
	public List<BoardVO> getAllPostList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int writePost(BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(String boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editPost(BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
