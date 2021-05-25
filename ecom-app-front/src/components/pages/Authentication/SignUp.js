import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './Auth.css';
import axios from 'axios';

export default class SignUp extends Component {
	constructor(props) {
		super(props);
		this.state = this.initialState;
		this.register = this.register.bind(this);
		this.registerChange = this.registerChange.bind(this);
	}

	initialState = {
		firstName: '',
		lastName: '',
		phone: '',
		email: '',
		password: ''
	};

	register = async (event) => {
		event.preventDefault();
		const user = {
			firstName: this.state.firstName,
			lastName: this.state.lastName,
			phone: this.state.phone,
			email: this.state.email,
			password: this.state.password
		};
		try {
			const res = await axios.post('http://localhost:8080/api/users', user);
			this.setState(this.initialState);
			alert('User registered succesfully');
			console.log(res);
		} catch (error) {
			console.log(error);
		}
	};

	registerChange(event) {
		this.setState({
			[event.target.name]: event.target.value
		});
	}

	render() {
		return (
			<form onSubmit={this.register} className="auth-inner">
				<h3>Sign Up</h3>

				<div className="form-group">
					<label>First name</label>
					<input
						value={this.state.firstName}
						onChange={this.registerChange}
						name="firstName"
						required
						type="text"
						className="form-control"
						placeholder="First name"
					/>
				</div>

				<div className="form-group">
					<label>Last name</label>
					<input
						value={this.state.lastName}
						onChange={this.registerChange}
						name="lastName"
						type="text"
						className="form-control"
						placeholder="Last name"
					/>
				</div>

				<div className="form-group">
					<label>Phone</label>
					<input
						value={this.state.phone}
						onChange={this.registerChange}
						name="phone"
						type="text"
						className="form-control"
						placeholder="Phone"
					/>
				</div>

				<div className="form-group">
					<label>Email address</label>
					<input
						value={this.state.email}
						onChange={this.registerChange}
						name="email"
						type="email"
						className="form-control"
						placeholder="Enter email"
					/>
				</div>

				<div className="form-group">
					<label>Password</label>
					<input
						value={this.state.password}
						onChange={this.registerChange}
						name="password"
						type="password"
						className="form-control"
						placeholder="Enter password"
					/>
				</div>

				<button type="submit" className="btn btn-primary btn-block">
					Sign Up
				</button>
				<p className="forgot-password text-right">
					Already registered <Link to="/login">sign in?</Link>
				</p>
			</form>
		);
	}
}
