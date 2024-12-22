package com.example.controller;

import com.example.dao.AlbumDao;
import com.example.dao.ArtistDao;
import com.example.models.Album;
import com.example.models.Artist;
import com.example.utils.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class AlbumController extends HttpServlet {

    private final AlbumDao albumDao = new AlbumDao();
    private final ArtistDao artistDao = new ArtistDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listAlbums(request, response);
                break;
            case "view":
                viewAlbum(request, response);
                break;
            case "delete":
                deleteAlbum(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            default:
                response.sendRedirect("albums?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("formAction");

        if ("save".equals(action)) {
            saveAlbum(request, response);
        } else if ("update".equals(action)) {
            updateAlbum(request, response);
        } else {
            response.sendRedirect("albums?action=list");
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("formAction");
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Album album = albumDao.getAlbumById(id);
            if (album != null) {
                request.setAttribute("album", album);
            }
        }
        List<Artist> artists = artistDao.getAllArtists();
        request.setAttribute("artists", artists);
        request.setAttribute("formAction", action);
        request.getRequestDispatcher("/WEB-INF/views/album-form.jsp").forward(request, response);
    }

    private void listAlbums(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Album> albums = albumDao.getAllAlbums();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("/WEB-INF/views/album-list.jsp").forward(request, response);
    }

    private void viewAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Album album = albumDao.getAlbumById(id);
        if (album != null) {
            request.setAttribute("album", album);
            request.getRequestDispatcher("/WEB-INF/views/album-view.jsp").forward(request, response);
        } else {
            response.sendRedirect("albums?action=list");
        }
    }

    private void saveAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));
        Artist artist = artistDao.getArtistById(artistId);
        Album album = new Album(title, genre, artist);
        albumDao.saveAlbum(album);
        response.sendRedirect("albums?action=list");
    }

    private void updateAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));
        Artist artist = artistDao.getArtistById(artistId);
        Album album = new Album(title, genre, artist);
        album.setAlbumId(id);
        albumDao.updateAlbum(album);
        response.sendRedirect("albums?action=list");
    }

    private void deleteAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        albumDao.deleteAlbum(id);
        response.sendRedirect("albums?action=list");
    }
}
