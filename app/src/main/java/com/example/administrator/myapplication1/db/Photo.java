package com.example.administrator.myapplication1.db;

public class Photo
{
	private String photoId;

	private String photoName;

	private String photoUrl;
	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}



	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}



	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	@Override
	public String toString() {
		return "Photo{" +
				"photoId='" + photoId + '\'' +
				", photoName='" + photoName + '\'' +
				", photoUrl='" + photoUrl + '\'' +
				'}';
	}

//	public String getClassId() {
//		return classId;
//	}
//	public void setClassId(String classId) {
//		this.classId = classId;
//	}
//	public String getClassName() {
//		return className;
//	}
//	public void setClassName(String className) {
//		this.className = className;
//	}
//	public String toString() {
//		return "Class--->"+"classId:"+classId+"  className:"+className;
//
//	}
}
