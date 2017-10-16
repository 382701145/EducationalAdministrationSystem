package com.yyw.eas.bean;


import java.util.List;

public class Course {

    private String schoolYear;
    private List<CourseInfo> courseInfoList;

    public List<CourseInfo> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public class CourseInfo {

        private String children;
        private String name;
        private String id;
        private String pId;
        private String target;
        private String url;
        private String icon;
        private String iconClose;
        private String iconOpen;
        private String iconSkin;
        private String checked;
        private String isParent;
        private String open;
        private String drop;
        private String IsProject;
        private String noselect;
        private String type;
        private String title;


        public String getChildren() {
            return children;
        }

        public void setChildren(String children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIconClose() {
            return iconClose;
        }

        public void setIconClose(String iconClose) {
            this.iconClose = iconClose;
        }

        public String getIconOpen() {
            return iconOpen;
        }

        public void setIconOpen(String iconOpen) {
            this.iconOpen = iconOpen;
        }

        public String getIconSkin() {
            return iconSkin;
        }

        public void setIconSkin(String iconSkin) {
            this.iconSkin = iconSkin;
        }

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public String getIsParent() {
            return isParent;
        }

        public void setIsParent(String isParent) {
            this.isParent = isParent;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getDrop() {
            return drop;
        }

        public void setDrop(String drop) {
            this.drop = drop;
        }

        public String getIsProject() {
            return IsProject;
        }

        public void setIsProject(String isProject) {
            IsProject = isProject;
        }

        public String getNoselect() {
            return noselect;
        }

        public void setNoselect(String noselect) {
            this.noselect = noselect;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "CourseInfo{" +
                    "children='" + children + '\'' +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", pId='" + pId + '\'' +
                    ", target='" + target + '\'' +
                    ", url='" + url + '\'' +
                    ", icon='" + icon + '\'' +
                    ", iconClose='" + iconClose + '\'' +
                    ", iconOpen='" + iconOpen + '\'' +
                    ", iconSkin='" + iconSkin + '\'' +
                    ", checked='" + checked + '\'' +
                    ", isParent='" + isParent + '\'' +
                    ", open='" + open + '\'' +
                    ", drop='" + drop + '\'' +
                    ", IsProject='" + IsProject + '\'' +
                    ", noselect='" + noselect + '\'' +
                    ", type='" + type + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "schoolYear='" + schoolYear + '\'' +
                ", courseInfoList=" + courseInfoList +
                '}';
    }
}
