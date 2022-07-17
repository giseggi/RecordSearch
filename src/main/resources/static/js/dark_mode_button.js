'use strict';

class DarkModeButton extends React.Component {

	constructor(props) {
		super(props);	
		
		var flg = document.getElementById('darkFlg');
		
		if(flg.value) {
			this.state = { isDark : true };
		} else {
			this.state = { isDark : false };
		}
	}

	render() {
		var ch = document.getElementById('style');
				
		var flg = document.getElementById('darkFlg');
		if (this.state.isDark) {

			if (ch) {
				ch.href = ('css/dark.css');
				flg.setAttribute('value', 'true');
			}
			return (
				<button onClick={() => this.setState({ isDark: false })}>
					Light
				</button>
			);

		} else {
			if (ch) {
				ch.href = ('css/light.css');
				flg.setAttribute('value', '');
			}
			return (
				<button onClick={() => this.setState({ isDark: true })}>
					dark
				</button>
			);
		}


	}
}

let domContainer = document.querySelector('#dark_mode_container');
ReactDOM.render(<DarkModeButton />, domContainer);