package com.place.helper.persistent.entity;

// Generated 2015-12-11 11:42:44 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PlacePage generated by hbm2java
 */
@Entity
@Table(name = "place_page", catalog = "weibopage")
public class PlacePage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String placeUrl;
	private String placePage;
	private Set<EncUrl> encUrls = new HashSet<EncUrl>(0);

	public PlacePage() {
	}

	public PlacePage(String placeUrl, String placePage, Set<EncUrl> encUrls) {
		this.placeUrl = placeUrl;
		this.placePage = placePage;
		this.encUrls = encUrls;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "place_url", length = 64)
	public String getPlaceUrl() {
		return this.placeUrl;
	}

	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}

	@Column(name = "place_page", length = 16777215)
	public String getPlacePage() {
		return this.placePage;
	}

	public void setPlacePage(String placePage) {
		this.placePage = placePage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "placePage")
	public Set<EncUrl> getEncUrls() {
		return this.encUrls;
	}

	public void setEncUrls(Set<EncUrl> encUrls) {
		this.encUrls = encUrls;
	}

}
