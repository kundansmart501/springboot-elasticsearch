package com.kundan.elasticsearchdemo.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "movieindex", type = "movietype", shards = 1, replicas = 0)
public class Movie {
	Long Id;
	String SupplierReference;
	String Title;
	String SortTitle;
	String ReleaseYear;
	
	public Movie() {
	}
	
	public Movie(Long id,String SupplierReference,String Title,String SortTitle,String ReleaseYear){
		
		this.Id = id;
		this.SupplierReference = SupplierReference;
		this.Title = Title;
		this.SortTitle = SortTitle;
		this.ReleaseYear = ReleaseYear;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getSupplierReference() {
		return SupplierReference;
	}

	public void setSupplierReference(String supplierReference) {
		SupplierReference = supplierReference;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getSortTitle() {
		return SortTitle;
	}

	public void setSortTitle(String sortTitle) {
		SortTitle = sortTitle;
	}

	public String getReleaseYear() {
		return ReleaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		ReleaseYear = releaseYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((ReleaseYear == null) ? 0 : ReleaseYear.hashCode());
		result = prime * result + ((SortTitle == null) ? 0 : SortTitle.hashCode());
		result = prime * result + ((SupplierReference == null) ? 0 : SupplierReference.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (ReleaseYear == null) {
			if (other.ReleaseYear != null)
				return false;
		} else if (!ReleaseYear.equals(other.ReleaseYear))
			return false;
		if (SortTitle == null) {
			if (other.SortTitle != null)
				return false;
		} else if (!SortTitle.equals(other.SortTitle))
			return false;
		if (SupplierReference == null) {
			if (other.SupplierReference != null)
				return false;
		} else if (!SupplierReference.equals(other.SupplierReference))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}
	
}
