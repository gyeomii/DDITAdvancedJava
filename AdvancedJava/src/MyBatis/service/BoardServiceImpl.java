package MyBatis.service;

import java.util.List;

import MyBatis.dao.BoardDaoImpl;
import MyBatis.dao.IBoardDao;
import MyBatis.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	private static IBoardService boardService;

	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	@Override
	public List<BoardVO> getAllPostList() {
		List<BoardVO> boardList = boardDao.getAllPostList();
		return boardList;
	}

	@Override
	public int writePost(BoardVO bv) {
		int cnt = boardDao.writePost(bv);
		return cnt;
	}

	@Override
	public int deletePost(String boardNo) {
		int cnt = boardDao.deletePost(boardNo);
		return cnt;
	}

	@Override
	public int editPost(BoardVO bv) {
		int cnt = boardDao.editPost(bv);
		return cnt;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		List<BoardVO> boardList = boardDao.searchPost(bv);
		return boardList;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean chk = boardDao.checkBoard(boardNo);
		return chk;
	}

}
