package fer.or.api.service;

import fer.or.api.model.BoardGame;
import fer.or.api.repository.BoardGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardGameService {
    @Autowired
    private final BoardGameRepo boardGameRepo;

    public BoardGameService(BoardGameRepo boardGameRepo) {
        this.boardGameRepo = boardGameRepo;
    }

    public List<BoardGame> getAllBoardGames() {
        return (List<BoardGame>) boardGameRepo.findAll();
    }

    public Optional<BoardGame> getBoardGameById(int id) {
        return boardGameRepo.findById(id);
    }

    public BoardGame addBoardGame(BoardGame boardGame) {
        return boardGameRepo.save(boardGame);
    }

    public void deleteBoardGame(int id) {
        boardGameRepo.deleteById(id);
    }

    public void updateBoardGame(BoardGame boardGame) {
        boardGameRepo.save(boardGame);
    }
}
