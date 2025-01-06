package fer.or.api.controller;

import fer.or.api.dto.BoardGameDTO;
import fer.or.api.model.*;
import fer.or.api.service.*;
import fer.or.api.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private BoardGameService boardGameService;
    @Autowired
    private DesignerService designerService;
    @Autowired
    private PublisherService publisherService;

    @Value("classpath:openapi.json")
    Resource openApiJson;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All artists fetched"))
                .body(artistService.getAllArtists());
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable int id) {
        Optional<Artist> artist = artistService.getArtistById(id);

        return artist.map(art -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Artist fetched")).body(art))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Artist with the provided ID doesn't exist")).build());
    }

    @GetMapping("/franchises")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All franchises fetched"))
                .body(franchiseService.getAllFranchises());
    }

    @GetMapping("/franchises/{id}")
    public ResponseEntity<Franchise> getFranchiseById(@PathVariable int id) {
        Optional<Franchise> franchise = franchiseService.getFranchiseById(id);

        return franchise.map(fr -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Franchise fetched")).body(fr))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Franchise with the provided ID doesn't exist")).build());
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All publishers fetched"))
                .body(publisherService.getAllPublishers());
    }

    @GetMapping("/publishers/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable int id) {
        Optional<Publisher> publisher = publisherService.getPublisherById(id);

        return publisher.map(pub -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Publisher fetched")).body(pub))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Publisher with the provided ID doesn't exist")).build());
    }

    @GetMapping("/boardGames")
    public ResponseEntity<List<BoardGame>> getAllBoardGames() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All board games fetched"))
                .body(boardGameService.getAllBoardGames());
    }

    @GetMapping("/boardGames/{id}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int id) {
        Optional<BoardGame> boardGame = boardGameService.getBoardGameById(id);
        ResponseEntity<BoardGame> response;

        return boardGame.map(game -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Board game fetched")).body(game))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Board game with the provided ID doesn't exist")).build());
    }

    @GetMapping("/designers")
    public ResponseEntity<List<Designer>> getAllDesigners() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All designers fetched"))
                .body(designerService.getAllDesigners());
    }

    @GetMapping("/designers/{id}")
    public ResponseEntity<Designer> getDesignerById(@PathVariable int id) {
        Optional<Designer> designer = designerService.getDesignerById(id);

        return designer.map(dsgnr -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Designer fetched")).body(dsgnr))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Designer with the provided ID doesn't exist")).build());
    }

    @PostMapping(path = "/boardGames/add", produces = "application/json")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGameDTO boardGameDTO) {
        BoardGame newBoardGame = new BoardGame();

        newBoardGame.setName(boardGameDTO.getName());
        newBoardGame.setReleaseYear(boardGameDTO.getReleaseYear());
        newBoardGame.setNumberOfPlayers(boardGameDTO.getNumberOfPlayers());
        newBoardGame.setAge(boardGameDTO.getAge());
        newBoardGame.setPlayingTime(boardGameDTO.getPlayingTime());

        newBoardGame.setFranchise(franchiseService.getFranchiseById(boardGameDTO.getFranchiseId()).orElse(null));
        newBoardGame.setPublisher(publisherService.getPublisherById(boardGameDTO.getPublisherId()).orElse(null));

        List<Artist> artists = artistService.getAllArtistsByIds(boardGameDTO.getArtistIds());
        newBoardGame.setArtists(artists);
        List<Designer> designers = designerService.getAllDesignersByIds(boardGameDTO.getDesignerIds());
        newBoardGame.setDesigners(designers);

        BoardGame addedBoardGame = boardGameService.addBoardGame(newBoardGame);

        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Board game added")).body(addedBoardGame);
    }

    @DeleteMapping("boardGames/delete/{id}")
    public ResponseEntity<BoardGame> deleteBoardGame(@PathVariable int id) {
        boardGameService.deleteBoardGame(id);

        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Board game deleted")).build();
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<BoardGame> updateBoardGame(@RequestBody BoardGameDTO boardGameDTO) {
        Optional<BoardGame> existingBoardGame = boardGameService.getBoardGameById(boardGameDTO.getId());

        if(existingBoardGame.isEmpty())
            return ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not Found", "Board game with the provided ID doesn't exist")).build();

        BoardGame boardGame = existingBoardGame.get();

        boardGame.setName(boardGameDTO.getName());
        boardGame.setReleaseYear(boardGameDTO.getReleaseYear());
        boardGame.setNumberOfPlayers(boardGameDTO.getNumberOfPlayers());
        boardGame.setAge(boardGameDTO.getAge());
        boardGame.setPlayingTime(boardGameDTO.getPlayingTime());

        Optional<Franchise> franchise = franchiseService.getFranchiseById(boardGameDTO.getFranchiseId());
        franchise.ifPresent(boardGame::setFranchise);

        Optional<Publisher> publisher = publisherService.getPublisherById(boardGameDTO.getPublisherId());
        publisher.ifPresent(boardGame::setPublisher);

        List<Artist> artists = artistService.getAllArtistsByIds(boardGameDTO.getArtistIds());
        boardGame.setArtists(artists);
        List<Designer> designers = designerService.getAllDesignersByIds(boardGameDTO.getDesignerIds());
        boardGame.setDesigners(designers);

        boardGameService.updateBoardGame(boardGame);

        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Board game updated")).body(boardGame);
    }

    @GetMapping("openAPI/specification")
    public ResponseEntity<String> getOpenAPISpecification() throws IOException {
        String body = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openApiJson.getInputStream(), "UTF-8"))) {
            body = br.lines().collect(Collectors.joining("\n"));
        }
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "OpenAPI specification fetched")).body(body);
    }
}
