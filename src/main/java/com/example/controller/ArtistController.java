package com.example.controller;

import com.example.dao.ArtistDao;
import com.example.models.Artist;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ArtistController extends HttpServlet {

    private final ArtistDao artistDao = new ArtistDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listArtists(request, response);
                break;
            case "view":
                viewArtist(request, response);
                break;
            case "delete":
                deleteArtist(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            default:
                response.sendRedirect("artists?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("formAction");

        if ("save".equals(action)) {
            saveArtist(request, response);
        } else if ("update".equals(action)) {
            updateArtist(request, response);
        } else {
            response.sendRedirect("artists?action=list");
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("formAction");
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDao.getArtistById(id);
            if (artist != null) {
                request.setAttribute("artist", artist);
            }
        }
        request.setAttribute("formAction", action);
        request.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(request, response);
    }
    private void listArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Artist> artists = artistDao.getAllArtists();
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("/WEB-INF/views/artist-list.jsp").forward(request, response);
    }

    private void viewArtist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Artist artist = artistDao.getArtistById(id);
        if (artist != null) {
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/views/artist-view.jsp").forward(request, response);
        } else {
            response.sendRedirect("artists?action=list");
        }
    }

    private void saveArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Artist artist = new Artist(name);
        artistDao.saveArtist(artist);
        response.sendRedirect("artists?action=list");
    }

    private void updateArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Artist artist = new Artist(name);
        artist.setArtistId(id);
        artistDao.updateArtist(artist);
        response.sendRedirect("artists?action=list");
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        artistDao.deleteArtist(id);
        response.sendRedirect("artists?action=list");
    }
}
