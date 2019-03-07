<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	/**
	 * 카테고리 삭제
	 */
	function deleteImg( no ) {
		// ajax connection
		$.ajax({
			url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/api/delete?no=" + no,
			type: "get",
			dataType: "json",
			data: "",
			success: function( JSONResult ) {
				if ( JSONResult.result == "fail" ) {
					console.error( JSONResult.message );
					return;
				}
	
				// failed to deletion
				if ( JSONResult.data === -1 ) {
					return;
				}
				
				$("tr.cat").remove();
				num = 1;
				FetchList();
			},
			error: function( jqXHR, status, e ) {
				console.error( status + " : " + e );
			}
		});
	}

	$(function() {
		num = 1;
		var render = function( vo, mode ) {
			var html = "<tr class='cat'>" +
						   "<td>" + (num++) + "</td>" +
						   "<td>" + vo.name + "</td>" +
						   "<td>" + vo.postCount + "</td>" +
						   "<td>" + vo.description + "</td>" +
						   "<td>" +
								"<img style='cursor: pointer;' id='delete-img' onclick='javascript: deleteImg(" + vo.no + ");'" +
									 "src='${pageContext.request.contextPath}/assets/images/delete.jpg'>" +
						   "</td>" +
					   "</tr>";

			if ( mode == false ) {
				$( "#list-category" ).prepend( html );
			} else {
				$( "#list-category" ).append( html );
			}
		}
		
		/**
		 * 카테고리 리스트 출력
		 */
		FetchList = function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/api/list",
				type: "get",
				dataType: "json",
				data: "",
				success: function( response ) {
					if ( response.result == "fail" ) {
						console.warn( response.message );
						return;
					}
	
					// rendering
					$.each( response.data, function(index, vo) {
						render( vo, false );
					});
				},
				error: function( jqXHR, status, e ) {
					console.error( status + ":" + e);
				}
			});
		}
		
		/**
		 * 카테고리 추가
		 */
		$( "#add-form" ).submit( function( event ) {
			// submit의 기본 동작(post)을 막아야 한다.
			event.preventDefault();
			
			// console.log( $( "#cat-name" ).val() );
			// console.log( $( "#cat-desc" ).val() );
			
			var vo = { };
			vo.name = $( "#cat-name" ).val();
			vo.description = $( "#cat-desc" ).val();
			
			// ajax connection
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category/api/add",
				type: "post",
				dataType: "json",
				data: JSON.stringify( vo ),
				contentType: 'application/json',
				success: function( response ) {
					if ( response.result == "fail" ) {
						console.error( response.message );
						return;
					}
					
					// rendering
					render( response.data, false );
					
					// reset form
					console.log( $("#add-form") );
					$( "#add-form" )[0].reset();
				},
				error: function( jqXHR, status, e ) {
					console.error( status + " : " + e );
				}
			});
		} );
		
		FetchList();
	});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-admin-nav.jsp">
					<c:param name="menu" value="category" />
				</c:import>
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
							<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		
		      		<tbody id="list-category">
						<!-- ajax -->
					</tbody>
					
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="add-form" action="" method="post">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" id="cat-name" name="name" required></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" id="cat-desc" name="description" required></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" id="btn-cat-add" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>