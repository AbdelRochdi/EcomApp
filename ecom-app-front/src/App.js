import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Navigation from './components/layout/Navigation';
import Footer from './components/layout/Footer';
import Home from './components/pages/home/Home';
import Single from './components/pages/single/Single';
import Cart from './components/pages/cart/Cart';
import './App.css';
import SignIn from './components/pages/Authentication/SignIn';
import SignUp from './components/pages/Authentication/SignUp';
import UserList from './components/pages/Admin/UserList';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
	render() {
		return (
			<BrowserRouter>
				<div className="App">
					<Navigation />
					<Switch>
						<Route path="/product/:id" component={Single} />
						<Route path="/cart" component={Cart} />
						<Route exact path="/" component={Home} />
						<Route exact path="/login" component={SignIn} />
						<Route exact path="/register" component={SignUp} />
						<Route exact path="/usersList" component={UserList} />
					</Switch>
					<Footer />
				</div>
			</BrowserRouter>
		);
	}
}

export default App;
