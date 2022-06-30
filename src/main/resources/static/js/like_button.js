'use strict';

class LikeButton extends React.Component {
	constructor(props) {
		super(props);
		this.state = { liked: false };
	}

	render() {
		var ch = document.getElementById('style')
		if (this.state.liked) {

			if (ch) {
				ch.href = ('css/dark.css');
			}
			return (
				<button onClick={() => this.setState({ liked: false })}>
					Light
				</button>
			);
			// document.querySelector('#div_header').style.backgroundColor = "red";
		} else {
			if (ch) {
				ch.href = ('css/light.css');
			}
			return (
				<button onClick={() => this.setState({ liked: true })}>
					dark
				</button>
			);
		}


	}
}

let domContainer = document.querySelector('#like_button_container');
ReactDOM.render(<LikeButton />, domContainer);