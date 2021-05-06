import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import './Navigation.css';

class Navigation extends Component {
	render() {
		return (
			<header>
				<div className="top-nav">
					<p className="adresse">
						Jordan Calderon 430-985 Eleifend St. Duluth Washington 92611 (427) 930-5255
					</p>
                    <nav className="options">
                        <ul>
                            <li> <Link to="/login">Login</Link></li>
                            <li> <Link to="/register">Register</Link></li>
                            <li> <Link to="/usersList">Users</Link></li>
                            <li>Wishlist</li>
                        </ul>
                    </nav>
				</div>
                <div className="bot-nav">
                    <Link style={{textDecoration:'none', color:'white'}} to="/"> <div className="logo">Shopee</div></Link>
                    
                    <nav className="nav-links">
                        <ul>
                            <li>On Sale</li>
                            <li>Category</li>
                            <li>Products</li>
                            <li>Blog</li>
                            <li>Coming Soon</li>
                        </ul>
                    </nav>
                    <Link style={{textDecoration:'none', color:'white'}} to="/cart"> <div className="cart">
                        <i className="fas fa-shopping-cart"></i>
                        <div className="item-number">0</div>
                    </div> </Link>
                </div>
			</header>
		);
	}
}

export default Navigation;
