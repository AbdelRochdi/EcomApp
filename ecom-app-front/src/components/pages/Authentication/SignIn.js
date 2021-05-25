import React, { Component } from 'react';
import './Auth.css';
import axios from 'axios';
import setJWToken from '../../../security/setJWToken';
import jwtDecode from 'jwt-decode';

export default class SignIn extends Component {
	constructor(props) {
		super(props);
		this.state = this.initialState;
		// this.loginUser = this.loginUser.bind(this);
		this.loginChange = this.loginChange.bind(this);
	}

	initialState = {
		email: '',
		password: ''
	};

	loginUser = async (event) => {
		event.preventDefault();
		const user = {
			email: this.state.email,
			password: this.state.password
		};
		try {
			const res = await axios.post('http://localhost:8080/api/users/login', user);
			this.setState(this.initialState);
			let token = res.data.token;
			localStorage.setItem('JwtToken', token);
			setJWToken(token);
			const decoded = jwtDecode(token);
			console.log(res);
			alert('User logged in succesfully');
		} catch (error) {
			console.log(error);
		}
	};

	loginChange(event) {
		this.setState({
			[event.target.name]: event.target.value
		});
	}

	render() {
		return (
			<form onSubmit={this.loginUser} className="auth-inner">
				<h3>Sign In</h3>

				<div className="form-group">
					<label>Email address</label>
					<input
						name="email"
						value={this.state.email}
						onChange={this.loginChange}
						required
						type="email"
						className="form-control"
						placeholder="Enter email"
					/>
				</div>

				<div className="form-group">
					<label>Password</label>
					<input
						name="password"
						value={this.state.password}
						onChange={this.loginChange}
						required
						type="password"
						className="form-control"
						placeholder="Enter password"
					/>
				</div>

				<div className="form-group">
					<div className="custom-control custom-checkbox">
						<input type="checkbox" className="custom-control-input" id="customCheck1" />
						<label className="custom-control-label" htmlFor="customCheck1">
							Remember me
						</label>
					</div>
				</div>

				<button type="submit" className="btn btn-primary btn-block">
					Submit
				</button>
				<p className="forgot-password text-right">
					Forgot <a href="#">password?</a>
				</p>
			</form>
		);
	}
}
