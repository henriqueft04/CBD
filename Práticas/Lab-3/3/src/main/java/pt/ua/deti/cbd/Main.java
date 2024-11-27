package pt.ua.deti.cbd;
// import cassandra dependencies
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("172.20.0.2",9042 ))
                .withLocalDatacenter("datacenter1")
                .withKeyspace("videos")
                .build()) {

            System.out.println("\n");
            System.out.println("Connected to cluster: " + session.getMetadata().getClusterName()+ " in keyspace " + session.getKeyspace().get() + " successfully \n");

            insertVideo(session, UUID.fromString("11111111-1111-1111-1111-111111111119"), "ana_silva", Instant.parse("2024-11-29T00:00:00Z"), "Playing Hotel California ft. CC", "Henrique Texeira ft. CC playing Hotel California" , Set.of("music", "guitar", "Aveiro"));

            selectVideosByUser(session, "ana_silva");

            updateVideoTitle(session, UUID.fromString("11111111-1111-1111-1111-111111111119"), Instant.parse("2024-11-29T00:00:00Z"), "Playing Hotel California ft. CC - Henrique Texeira ft. CC");

            // 1. Os últimos 3 comentários introduzidos para um vídeo;
            UUID videoId = UUID.fromString("11111111-1111-1111-1111-111111111111");
            getLastThreeComments(session, videoId);

            // 2. Lista das tags de determinado vídeo;
            getTagsByVideoId(session, videoId);

            // 3. Lista de vídeos que contêm uma determinada tag;
            getVideosByTag(session, "tutorial");

            // 4. Os últimos 5 eventos realizados por um utilizador num vídeo;
            getLastFiveEvents(session, "ana_silva", videoId);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateVideoTitle(CqlSession session, UUID video_id, Instant upload_date, String new_title) {
        System.out.println("-------------------");
        System.out.println("Updating video title");
        String query = "UPDATE video SET title = ? WHERE video_id = ? and upload_date = ?";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(new_title, video_id, upload_date);

        ResultSet rs = session.execute(bound);

        if (rs.wasApplied()) {
            System.out.println("Title updated successfully! \n New title: " + new_title);
        } else {
            System.out.println("Title update failed!");
        }
        System.out.println("------------------- \n");
    }

    private static void insertVideo(CqlSession session, UUID video_id,String user,  Instant upload_date, String title, String description, Set tags) {
        System.out.println("-------------------");
        System.out.println("Inserting a video");
        String query = "INSERT INTO video (video_id, upload_date, title, user,  description, tags) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(video_id, upload_date, title, user, description, tags);

        ResultSet rs = session.execute(bound);

        if (rs.wasApplied()) {
            System.out.println("Video inserted successfully!");
        } else {
            System.out.println("Video insert failed!");
        }

        // should also insert it into the videos_by_user table
        query = "INSERT INTO videos_by_user (user, video_id, upload_date, title, description, tags) VALUES (?, ?, ?, ?, ?, ?)";
        prepared = session.prepare(query);

        bound = prepared.bind(user, video_id, upload_date, title, description, tags);

        rs = session.execute(bound);

        if (rs.wasApplied()) {
            System.out.println("Video inserted into videos_by_user successfully! ");
        } else {
            System.out.println("Video insert into videos_by_user failed!");
        }

        System.out.println("-------------------\n");

    }

    private static void selectVideosByUser(CqlSession session, String user) {
        System.out.println("-------------------");
        System.out.println("Selecting videos uploaded by a user");
        String query = "SELECT * FROM videos_by_user WHERE user = ?";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(user);

        ResultSet rs = session.execute(bound);

        System.out.println("Videos uploaded by user " + user + ": \n");

        rs.forEach(row -> {
            System.out.println("Title: " + row.getString("title"));
            System.out.println("    Video ID: " + row.getUuid("video_id"));
            System.out.println("    Upload Date: " + row.getInstant("upload_date"));
            System.out.println("    Description: " + row.getString("description"));
            System.out.println("    Tags: " + row.getSet("tags", String.class));
        });

        System.out.println("------------------- \n");
    }

    private static void getLastThreeComments(CqlSession session, UUID videoId) {
        System.out.println("-------------------");
        String query = "SELECT * FROM comment_by_video WHERE video_id = ? LIMIT 3";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(videoId);

        ResultSet rs = session.execute(bound);

        System.out.println("Últimos 3 comentários do vídeo " + videoId + ":");
        rs.forEach(row -> {
            System.out.println("User: " + row.getString("user"));
            System.out.println("    Comment ID: " + row.getUuid("comment_id"));
            System.out.println("    Comment Date: " + row.getInstant("comment_date"));
            System.out.println("    Comment: " + row.getString("comment") + "\n");
        });
        System.out.println("------------------- \n");
    }

    private static void getTagsByVideoId(CqlSession session, UUID videoId) {
        System.out.println("-------------------");
        String query = "SELECT tags, title FROM video WHERE video_id = ?";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(videoId);

        ResultSet rs = session.execute(bound);

        Row row = rs.one();
        String tile = row.getString("title");

        System.out.println("Tags do video '" + tile + "':");

        if (row == null) {
            System.out.println("Nenhuma tag encontrada para o vídeo " + videoId);
        } else {
            for (String tag : row.getSet("tags", String.class)) {
                System.out.println("\t->" +tag);
            }
        }
        System.out.println("------------------- \n");
    }

    private static void getVideosByTag(CqlSession session, String tag) {
        System.out.println("-------------------");
        String query = "SELECT * FROM video WHERE tags CONTAINS ?";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(tag);

        ResultSet rs = session.execute(bound);

        System.out.println("Vídeos com a tag '" + tag + "':");
        rs.forEach(row -> {
            System.out.println("Video ID: " + row.getUuid("video_id"));
            System.out.println("Title: " + row.getString("title"));
            System.out.println("Description: " + row.getString("description"));
            System.out.println("Tags: " + row.getSet("tags", String.class) + "\n");
        });
        System.out.println("------------------- \n");
    }

    private static void getLastFiveEvents(CqlSession session, String user, UUID videoId) {
        System.out.println("-------------------");
        System.out.println("Últimos 5 eventos realizados pelo user '" + user + "' no vídeo " + videoId);

        String query = "SELECT * FROM events_by_user WHERE user = ? AND video_id = ? LIMIT 5";
        PreparedStatement prepared = session.prepare(query);

        BoundStatement bound = prepared.bind(user, videoId);

        ResultSet rs = session.execute(bound);


        rs.forEach(row -> {
                System.out.println("Event Type: " + row.getString("event_type"));
                System.out.println("    Event Date: " + row.getInstant("event_date"));
                System.out.println();
        });
        System.out.println("------------------- \n");
    }

}