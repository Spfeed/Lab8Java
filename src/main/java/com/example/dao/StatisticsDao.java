package com.example.dao;

import com.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class StatisticsDao {

    public List<Object[]> getTracksWithAlbumsAndArtists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT t.title, t.duration, a.title, ar.name " +
                       "FROM Track t " +
                       "JOIN t.album a " +
                       "JOIN a.artist ar", Object[].class)
                    .getResultList();
        }
    }

    public List<Object[]> getAlbumsWithTrackCountAndTotalDuration() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT a.title, COUNT(t.trackId), SUM(t.duration) " +
                       "FROM Album a " +
                       "JOIN a.tracks t " +
                       "GROUP BY a.title", Object[].class)
                    .getResultList();
        }
    }
}
