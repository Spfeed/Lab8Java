package com.example.controller;

import com.example.dao.AlbumDao;
import com.example.dao.ArtistDao;
import com.example.dao.TrackDao;
import com.example.dto.AlbumDto;
import com.example.dto.ArtistDto;
import com.example.models.Album;
import com.example.models.Artist;
import com.example.models.Track;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ApiController extends HttpServlet {

    private final ArtistDao artistDao = new ArtistDao();
    private final AlbumDao albumDao = new AlbumDao();
    private final TrackDao trackDao = new TrackDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if ("getArtists".equals(action)) {
            List<Artist> artists = artistDao.getAllArtists();
            List<ArtistDto> artistDtos = artists.stream()
                            .map(this::convertToArtistDto).collect(Collectors.toList());
            response.getWriter().write(new Gson().toJson(artistDtos));
        } else if ("getAlbums".equals(action)) {
            List<Album> albums = albumDao.getAllAlbums();
            List<AlbumDto> albumDtos = albums.stream()
                            .map(this::convertToAlbumDto).collect(Collectors.toList());
            response.getWriter().write(new Gson().toJson(albumDtos));
        } else if ("getAlbumSuggestions".equals(action)) {
            String query = request.getParameter("query");
            if (query != null && !query.trim().isEmpty()) {
                List<Album> albums = albumDao.findAlbumsByTitle(query);
                List<String> suggestions = albums.stream().map(Album::getTitle).collect(Collectors.toList());
                response.getWriter().write(new Gson().toJson(suggestions));
            }
            else {
                response.getWriter().write("[]");
            }
        } else if ("getArtistSuggestions".equals(action)) {
            String query = request.getParameter("query");
            if (query != null && !query.trim().isEmpty()) {
                List<Artist> artists = artistDao.findArtistsByName(query);
                List<String> suggestions = artists.stream().map(Artist::getName).collect(Collectors.toList());
                response.getWriter().write(new Gson().toJson(suggestions));
            } else {
                response.getWriter().write("[]");
            }
        } else if ("getTrackSuggestions".equals(action)) {
            String query = request.getParameter("query");
            if (query != null && !query.trim().isEmpty()) {
                List<Track> tracks = trackDao.findTracksByTitle(query);
                List<String> suggestions = tracks.stream().map(Track::getTitle).collect(Collectors.toList());
                response.getWriter().write(new Gson().toJson(suggestions));
            } else {
                response.getWriter().write("[]");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректное действие");
        }
    }

    public ArtistDto convertToArtistDto(Artist artist) {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setArtistId(artist.getArtistId());
        artistDto.setName(artist.getName());
        return artistDto;
    }

    public AlbumDto convertToAlbumDto(Album album) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumId(album.getAlbumId());
        albumDto.setTitle(album.getTitle());
        albumDto.setGenre(album.getGenre());
        albumDto.setArtistId(album.getArtist().getArtistId());
        return albumDto;
    }
}
