import React, { Component } from 'react';
import './Movie.css';

class Movie extends Component {
    render() {
        return (
            <div className="Movie">
                <h1>극한직업</h1>
                <img src="https://file.mk.co.kr/meet/neds/2019/01/image_readtop_2019_47065_15482039843612795.jpg" alt="" />
            </div>
        );
    }
}

export default Movie;