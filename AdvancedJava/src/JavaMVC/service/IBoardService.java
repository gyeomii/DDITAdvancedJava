package JavaMVC.service;

import java.util.List;

import JavaMVC.vo.BoardVO;
/**
 * Post(게시물) 정보 처리를 수행하는 Service
 * @author Gyeom
 *
 */
public interface IBoardService {
	/**
	 *게시글 전체 목록을 가져오는 메서드
	 * @return 게시글 정보를 담고있는 List 객체
	 */
	public List<BoardVO> getAllPostList();
	
	/**
	 * 주어진 게시글 NO가 존재하는지 여부를 알아내는 메서드 
	 * @param boardNo 게시글NO
	 * @return 해당 게시글 NO가 존재하면 true, 존재하지 않으면 false
	 */
	public int writePost(BoardVO bv);
	
	/**
	 * 게시글정보를 삭제하는 메서드
	 * @param boardNo 삭제할 게시글NO
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int deletePost(String boardNo);
	
	/**
	 * 게시글 정보를 수정하는 메서드
	 * @param bv 게시글정보 객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int editPost(BoardVO bv);
	

	/**
	 * 게시글을 검색하는 메서드 
	 * @param bv 게시글정보 객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public List<BoardVO> searchPost(BoardVO bv);
	
	/**
	 * 주어진 게시글 NO가 존재하는지 여부를 알아내는 메서드 
	 * @param boardNo 게시글NO
	 * @return 해당 게시글 NO가 존재하면 true, 존재하지 않으면 false
	 */
	public boolean checkBoard(String boardNo);

}
