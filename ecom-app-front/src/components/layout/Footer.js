import React, { Component } from 'react';
import './Footer.css'

export class Footer extends Component {
	render() {
		return (
			<div className="footer">
                <div className="wrapper">
				<div className="logo">
					<h3>Mobile Shopee</h3>
					<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Repellendus, deserunt.</p>
				</div>
                <div className="newsletter">
                    <h3>Newsletter</h3>
                    <input type="text" placeholder="Email"/>
                    <button>Subscribe</button>
                </div>
                <div className="information">
                    <h3>Information</h3>
                    <ul>
                        <li>About Us</li>
                        <li>Delivery Information</li>
                        <li>Privacy Policy</li>
                        <li>Terms & Conditions</li>
                    </ul>
                </div>
                <div className="account">
                    <h3>Account</h3>
                    <ul>
                        <li>My Account</li>
                        <li>Order History</li>
                        <li>Wish List</li>
                        <li>Newsletters</li>
                    </ul>
                </div>
                </div>
                <p className="copyright">
                © Copyrights 2020. Design By Daily Tuition
                </p>
			</div>
		);
	}
}

export default Footer;
