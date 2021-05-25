import axios from 'axios';

const setJWToken = (token) => {
	if (token) {
		axios.defaults.header.common['Authorization'] = token;
	} else {
		delete axios.defaults.header.common['Authorization'];
	}
};

export default setJWToken;
