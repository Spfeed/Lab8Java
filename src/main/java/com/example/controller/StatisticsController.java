package com.example.controller;

import com.example.dao.StatisticsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class StatisticsController extends HttpServlet {

    private final StatisticsDao statisticsDao = new StatisticsDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
          showQuerySelectionPage(request, response);
        } else if ("tracks".equals(action)) {
            showTracksWithAlbumsAndArtists(request, response);
        } else if ("albums".equals(action)) {
            showAlbumsWithTrackCountAndDuration(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Действие не определено");
        }
    }

    private void showQuerySelectionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/select-query.jsp").forward(request, response);
    }

    private void showTracksWithAlbumsAndArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> tracks = statisticsDao.getTracksWithAlbumsAndArtists();
        request.setAttribute("tracks", tracks);
        request.getRequestDispatcher("/WEB-INF/views/tracks-with-albums-and-artists.jsp").forward(request, response);
    }

    private void showAlbumsWithTrackCountAndDuration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> albums = statisticsDao.getAlbumsWithTrackCountAndTotalDuration();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("/WEB-INF/views/albums-with-track-count.jsp").forward(request, response);
    }
}
