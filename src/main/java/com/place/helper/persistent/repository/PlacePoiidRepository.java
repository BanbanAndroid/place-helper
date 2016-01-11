package com.place.helper.persistent.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.place.helper.db.BaseRepository;
import com.place.helper.persistent.entity.PlacePoiid;

public interface PlacePoiidRepository extends BaseRepository<PlacePoiid, Integer>{

	@Query("select p from PlacePoiid p where p.poiId=:poiId")
	PlacePoiid findByPoiId(@Param("poiId")String poiId);

}
