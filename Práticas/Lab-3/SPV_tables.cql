CREATE KEYSPACE IF NOT EXISTS videos WITH REPLICATION =
{ 'class' : 'SimpleStrategy',
'replication_factor' : '1'
};

CREATE TABLE IF NOT EXISTS videos.user (
    username text PRIMARY KEY,
    name text,
    email text,
    register_date timestamp,
);

CREATE TABLE IF NOT EXISTS videos.video (
    video_id uuid PRIMARY KEY,
    title text,
    user text,
    upload_date timestamp,
    description text,
    tags set<text>,
);

CREATE TABLE IF NOT EXISTS videos.videos_by_user (
    user text,
    video_id uuid,
    title text,
    upload_date timestamp,
    description text,
    tags set<text>,
    PRIMARY KEY (user, video_id)
);

CREATE TABLE IF NOT EXISTS videos.comment_by_video (
    comment_id uuid,
    video_id uuid,
    user text,
    comment_date timestamp,
    comment text,
    PRIMARY KEY (video_id, comment_id)
) with clustering order by (comment_date desc);

CREATE TABLE IF NOT EXISTS videos.video_follower (
    user text,
    video_id uuid,
    user_timestamp timestamp,
    PRIMARY KEY (user, video_id)
);

CREATE TABLE IF NOT EXISTS videos.events_by_user (
    user text,
    video_id uuid,
    event_date timestamp,
    event_type text,
    PRIMARY KEY ((user, video_id), event_date)
);

CREATE TABLE IF NOT EXISTS videos.video_rating (
    video_id uuid,
    rating int,
    PRIMARY KEY (video_id, rating)
);

CREATE TABLE IF NOT EXISTS videos.average_video_rating (
    video_id uuid PRIMARY KEY,
    avg_rating float,
    total_ratings int
);