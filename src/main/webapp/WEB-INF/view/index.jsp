<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/khach-hang/add" method="post" modelAttribute="kh">
    <div>
        <label>Mã khách</label>
        <form:input path="ma" />
        <form:errors path="ma" cssStyle="color: red"/>
    </div>
    <div>
        <label>Tên khách</label>
        <form:input path="ten"/>
        <form:errors path="ten" cssStyle="color: red"/>
    </div>
    <div>
        <label>Loại khách</label>
        <form:select path="loai" id="loai">
            <form:option value="">--Chọn loại--</form:option>
            <form:option value="Vip">Vip</form:option>
            <form:option value="Thường">Thường</form:option>
        </form:select>
        <form:errors path="loai" cssStyle="color: red"/>
    </div>
    <div>
        <label>Giới tính</label>
        <form:radiobutton path="gioiTinh" value="false"/>Nam
        <form:radiobutton path="gioiTinh" value="true"/>Nữ
        <form:errors path="gioiTinh" cssStyle="color: red"/>
    </div>
    <div>
        <label>Tuổi</label>
        <form:input path="tuoi" id="tuoi"/>
        <form:errors path="tuoi" cssStyle="color: red"/>
        <span style="color:red;">${er != null ? er : ""}</span>
    </div>
    <div>
        <button type="submit">Add</button>
    </div>
</form:form>
<table>
    <thead>
        <tr>
            <th>Mã khách</th>
            <th>Tên khách</th>
            <th>Giới tính</th>
            <th>Tuổi</th>
            <th>Loại khách</th>
            <th>Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listkh}" var="kh">
            <tr>
                <td>${kh.ma}</td>
                <td>${kh.ten}</td>
                <td>${kh.gioiTinh == true ? "Nữ": "Nam"}</td>
                <td>${kh.tuoi}</td>
                <td>${kh.loai}</td>
                <td>
                    <a href="/khach-hang/view-update/${kh.ma}"><button>Sửa</button></a>
                    <a href="/khach-hang/remove/${kh.ma}"><button>Xóa</button></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>