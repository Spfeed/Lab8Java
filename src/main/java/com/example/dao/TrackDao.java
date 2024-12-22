package com.example.dao;

import com.example.models.Track;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TrackDao {

    public List<Track> getAllTracks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Track", Track.class).getResultList();
        }
    }

    public Track getTrackById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Track.class, id);
        }
    }

    public void saveTrack(Track track) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(track);
            transaction.commit();
        }
    }

    public void updateTrack(Track track) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Track existingTrack = session.get(Track.class, track.getTrackId());
            if (existingTrack != null) {
                existingTrack.setTitle(track.getTitle());
                existingTrack.setDuration(track.getDuration());
                existingTrack.setAlbum(track.getAlbum());
                session.update(existingTrack);
            }
            transaction.commit();
        }
    }

    // Удалить трек по ID
    public void deleteTrack(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Track track = session.get(Track.class, id);
            if (track != null) {
                session.delete(track);
            }

            transaction.commit();
        }
    }

    public List<Track> findTracksByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Track WHERE title ILIKE :query", Track.class)
                    .setParameter("query", title + "%")
                    .setMaxResults(10)
                    .getResultList();
        }
    }
}
