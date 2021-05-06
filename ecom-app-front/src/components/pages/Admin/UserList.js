import React, { Component } from 'react';
import axios from 'axios';
import { Card, Table } from 'react-bootstrap';

export default class UserList extends Component {
	state = {
		users: []
	};

	async componentDidMount() {
		try {
			const res = await axios.get('http://localhost:8080/api/users');
			this.setState({ users: res.data });
			console.log(res.data);
		} catch (error) {
			console.log(error);
		}
	}

	render() {
		return (
			<Card className={'border border-dark bg-dark text-white'}>
				<Card.Header>Users List</Card.Header>
				<Card.Body>
					<Table bordered hover striped variant="dark">
						<thead>
							<tr>
								<th>UserId</th>
								<th>FirstName</th>
								<th>LastName</th>
								<th>Email</th>
								<th>Phone</th>
								<th>Status</th>
								<th>Role</th>
							</tr>
						</thead>
						<tbody>
							{this.state.users.length === 0 ? (
								<tr align="center">
									<td colSpan="6"> No users available</td>
								</tr>
							) : (
								this.state.users.map((user) => (
									<tr key={user.userId}>
										<td>{user.userId}</td>
										<td>{user.firstName}</td>
										<td>{user.lastName}</td>
										<td>{user.email}</td>
										<td>{user.phone}</td>
										<td>{user.emailVerificationStatus ? 'enabled' : 'disabled'}</td>
										<td>{user.userRole.title}</td>
									</tr>
								))
							)}
						</tbody>
					</Table>
				</Card.Body>
			</Card>
		);
	}
}
