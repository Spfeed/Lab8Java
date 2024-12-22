package com.example.controller;

import com.example.dao.AlbumDao;
import com.example.dao.TrackDao;
import com.example.models.Album;
import com.example.models.Track;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TrackController extends HttpServlet {

    private final TrackDao trackDao = new TrackDao();
    private final AlbumDao albumDao = new AlbumDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTracks(request, response);
                break;
            case "view":
                viewTrack(request, response);
                break;
            case "delete":
                deleteTrack(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            default:
                response.sendRedirect("tracks?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("formAction");

        if ("save".equals(action)) {
            saveTrack(request, response);
        } else if ("update".equals(action)) {
            updateTrack(request, response);
        } else {
            response.sendRedirect("tracks?action=list");
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("formAction");
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Track track = trackDao.getTrackById(id);
            if (track != null) {
                request.setAttribute("track", track);
            }
        }
        List<Album> albums = albumDao.getAllAlbums();
        request.setAttribute("albums", albums);
        request.setAttribute("formAction", action);
        request.getRequestDispatcher("/WEB-INF/views/track-form.jsp").forward(request, response);
    }

    private void listTracks(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        List<Track> tracks = trackDao.getAllTracks();
        request.setAttribute("tracks", tracks);
        request.getRequestDispatcher("/WEB-INF/views/track-list.jsp").forward(request, response);
    }

    private void viewTrack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Track track = trackDao.getTrackById(id);
        if (track != null) {
            request.setAttribute("track", track);
            request.getRequestDispatcher("/WEB-INF/views/track-view.jsp").forward(request, response);
        } else {
            response.sendRedirect("tracks?action=list");
        }
    }

    private void saveTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        int duration = Integer.parseInt(request.getParameter("duration"));
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        Album album = albumDao.getAlbumById(albumId);
        Track track = new Track(title, duration, album);
        trackDao.saveTrack(track);
        response.sendRedirect("tracks?action=list");
    }

    private void updateTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int duration = Integer.parseInt(request.getParameter("duration"));
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        Album album = albumDao.getAlbumById(albumId);
        Track track = new Track(title, duration, album);
        track.setTrackId(id);
        trackDao.updateTrack(track);
        response.sendRedirect("tracks?action=list");
    }

    private void deleteTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        trackDao.deleteTrack(id);
        response.sendRedirect("tracks?action=list");
    }
}
