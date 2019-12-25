<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/22/2019
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">

            <img style="width: 100%; height: auto;" src="${pageContext.request.contextPath}/resources/images/back1.jpg" alt="First slide"/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Welcome to My Music Store</h1>
                    <p>Here you can browse and buy Instruments, Records and music related Accessories. Order Now for
                        Your Amazing New Kit!</p>

                </div>
            </div>
        </div>
        <div class="carousel-item">
            <img style="width: 100%; height: auto;" src="${pageContext.request.contextPath}/resources/images/back2.jpg" alt="First slide" />
            <div class="container">
                <div class="carousel-caption">
                    <h1>Facts You Should Know About Music</h1>
                    <p>Music brings joy, to all of our hearts, It's one of those, emotional arts.</p>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <img style="width: 100%; height: auto;"  src="${pageContext.request.contextPath}/resources/images/back3.jpg" alt="First slide" />
            <div class="container">
                <div class="carousel-caption">
                    <h1>It's A Jazz Affair</h1>
                    <p>Through ups and downs, Somehow I manage to survive in life.</p>
                </div>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">

            <img style="width: 100px;" class="img-circle" src="${pageContext.request.contextPath}/resources/images/instrument.jpg" alt="Instrument" />

            <h2>Instrument</h2>
            <p>Well crafted and delicate instruments.</p>

        </div>
        <div class="col-lg-4">

            <img style="width: 100px;" class="img-circle" src="${pageContext.request.contextPath}/resources/images/record.jpg" alt="Record" />

            <h2>Discography</h2>
            <p>An exceptional collections of music records in favor of the traditional and modern genre of jazz.</p>
        </div>

        <div class="col-lg-4">

            <img style="width: 100px;" class="img-circle" src="${pageContext.request.contextPath}/resources/images/accessory.jpg" alt="Accessory" />

            <h2>Accessories</h2>
            <p>All musical and related geeky goods..</p>
        </div>
    </div>

</div>