package com.example.YouTube.repository;

import com.example.YouTube.entity.VideoEntity;
import com.example.YouTube.mapper.VideoShortInfoMapper;

import com.example.YouTube.mapper.VideoShortInfoPaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface VideoRepository extends CrudRepository<VideoEntity, String>, PagingAndSortingRepository<VideoEntity, String> {
    @Query("from VideoEntity where id=?1")
    Optional<Boolean> findByVideoId(String videoId);
    /// bug
  /*  Page<VideoEntity> findByCategoryId(Pageable categoryId, boolean pageable);
    Page<VideoShortInfoMapper> findByTagId(Long tagId, Pageable pageable);
    Page<VideoShortInfoMapper> findByTitleContaining(String title, Pageable pageable);*/
   /* @Query(value = "select v.id as videoId, v.title, v.preview_attach_id, v.published_date,  v.view_count," +
            "       c.id as channelId, c.name as channelName, c.photo_id" +
            " from video as v " +
            "         inner join channel as c on c.id = v.channel_id " +
            "         inner join profile as p on p.id = c.profile_id " +
            " where v.id = ?1", nativeQuery = true)
    List<VideoShortInfoMapper> getVideoShortInfo(String id);
    //   VidePlayListInfo(id,title, preview_attach(id,url), view_count,
    //                       published_date,duration)

    @Query(value = "select v.id as videoId, v.title, v.preview_attach_id, v.published_date,  v.view_count, " +
            "    c.id as channelId, c.name, c.photo_id, " +
            "    p.id as profileId,p.name as profileName,p.surname, " +
            "    (select  cast(json_agg(temp_t) as text) " +
            "     from (select json_build_object('id', pl.id, 'name', pl.name) from playlist as pl " +
            "                 inner join play_list_video as plv on plv.playlist_id = pl.id " +
            "                 inner join video vv on vv.id = plv.video_id " +
            "           where vv.id = v.id) as temp_t) as playListJson " +
            "from video as v " +
            "inner join channel as c on c.id = v.channel_id " +
            "inner join profile as p on p.id = c.profile_id " +
            " order by v.creted_date desc", nativeQuery = true)
    Page<VideoShortInfoPaginationMapper> getVideoListForAdmin(Pageable pageable);*/


    //  (VideShortInfo + owner (is,name,surname) + [playlist (id,name))]

}
