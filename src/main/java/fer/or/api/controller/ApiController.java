package fer.or.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fer.or.api.dto.BoardGameDTO;
import fer.or.api.model.*;
import fer.or.api.service.*;
import fer.or.api.util.FileUtil;
import fer.or.api.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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

    @Value("classpath:data.csv")
    Resource dataCsv;

    @Value("classpath:data.json")
    Resource dataJson;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All artists fetched"))
                .body(artistService.getAllArtists());
    }

    @GetMapping(value = "/artists/{id}", produces = "application/json")
    public ResponseEntity<?> getArtistById(@PathVariable int id) throws JsonProcessingException {
        Optional<Artist> artist = artistService.getArtistById(id);

        if(artist.isEmpty()) {
            return ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Artist with the provided ID doesn't exist")).build();
        }

        String context = "\"@context\":{" +
                "\"@vocab\":\"https://schema.org/\"," +
                "\"name\":\"givenName\"," +
                "\"surname\":\"familyName\"},";
        ObjectMapper mapper = new ObjectMapper();
        String artistStr = mapper.writeValueAsString(artist.get());

        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Artist fetched"))
                .body("{" + context + artistStr + "}");
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

        return boardGame.map(game -> ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Board game fetched")).body(game))
                .orElseGet(() -> ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Board game with the provided ID doesn't exist")).build());
    }

    @GetMapping("/designers")
    public ResponseEntity<List<Designer>> getAllDesigners() {
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "All designers fetched"))
                .body(designerService.getAllDesigners());
    }

    @GetMapping(value = "/designers/{id}", produces = "application/json")
    public ResponseEntity<?> getDesignerById(@PathVariable int id) throws JsonProcessingException {
        Optional<Designer> designer = designerService.getDesignerById(id);

        if(designer.isEmpty()) {
            return ResponseEntity.notFound().headers(HeaderUtil.createHeaders("Not found", "Designer with the provided ID doesn't exist")).build();
        }

        String context = "\"@context\":{" +
                "\"@vocab\":\"https://schema.org/\"," +
                "\"name\":\"givenName\"," +
                "\"surname\":\"familyName\"},";
        ObjectMapper mapper = new ObjectMapper();
        String designerStr = mapper.writeValueAsString(designer.get());

        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "Designer fetched"))
                .body("{" + context + designerStr + "}");
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

    @GetMapping("/boardGames/datatable")
    public String datatable(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "datatable";
    }

    @GetMapping("/openAPI/specification")
    public ResponseEntity<String> getOpenAPISpecification() throws IOException {
        String body;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openApiJson.getInputStream(), StandardCharsets.UTF_8))) {
            body = br.lines().collect(Collectors.joining("\n"));
        }
        return ResponseEntity.ok().headers(HeaderUtil.createHeaders("OK", "OpenAPI specification fetched")).body(body);
    }

    @GetMapping(
            value = "/boardGames/data.csv",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<InputStreamResource> getDataCsv() throws IOException {
        Resource resource = new ClassPathResource("/data.csv");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping(
            value = "/boardGames/data.json",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<InputStreamResource> getDataJson() throws IOException {
        Resource resource = new ClassPathResource("/data.json");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.json");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }

    @GetMapping("/korisnik")
    public String user(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "korisnik";
    }

    @GetMapping("/prijava")
    public String login(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "redirect";
    }

    @PostMapping("/osvjeziPreslike")
    public String refreshFiles(Model model, @AuthenticationPrincipal OidcUser principal) throws IOException {
        if(principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }

        List<BoardGame> boardGames = boardGameService.getAllBoardGames();
        ObjectMapper mapper = new ObjectMapper();

        String newJson = mapper.writeValueAsString(boardGames);

        FileUtil.overwriteFileWithString(dataJson, newJson);

        String newCsv = "board_game_id,name,release_year," +
                "number_of_players,age,playing_time," +
                "franchise_id,franchise_name," +
                "publisher_id,publisher_name," +
                "designer_id,designer_name,designer_surname," +
                "artist_id,artist_name,artist_surname\n";

        for(BoardGame boardGame : boardGames)
            for(Artist artist : boardGame.getArtists())
                for(Designer designer : boardGame.getDesigners())
                    newCsv += boardGame.getId() + "," + boardGame.getName() + "," + boardGame.getReleaseYear() + "," +
                        boardGame.getNumberOfPlayers() + boardGame.getAge() + boardGame.getPlayingTime() +
                        boardGame.getFranchise().getId() + boardGame.getFranchise().getName() +
                        boardGame.getPublisher().getId() + boardGame.getPublisher().getName() +
                        designer.getName() + designer.getName() + designer.getSurname() +
                        artist.getId() + artist.getName() + artist.getSurname() + "\n";

        FileUtil.overwriteFileWithString(dataCsv, newCsv);
        return "redirect";
    }
}
