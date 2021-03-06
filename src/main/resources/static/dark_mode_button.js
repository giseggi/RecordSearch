'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var DarkModeButton = function (_React$Component) {
	_inherits(DarkModeButton, _React$Component);

	function DarkModeButton(props) {
		_classCallCheck(this, DarkModeButton);

		var _this = _possibleConstructorReturn(this, (DarkModeButton.__proto__ || Object.getPrototypeOf(DarkModeButton)).call(this, props));

		var flg = document.getElementById('darkFlg');

		if (flg.value) {
			_this.state = { isDark: true };
		} else {
			_this.state = { isDark: false };
		}
		return _this;
	}

	_createClass(DarkModeButton, [{
		key: 'render',
		value: function render() {
			var _this2 = this;

			var ch = document.getElementById('style');

			var flg = document.getElementById('darkFlg');
			if (this.state.isDark) {

				if (ch) {
					ch.href = 'css/dark.css';
					flg.setAttribute('value', 'true');
				}
				return React.createElement(
					'button',
					{ onClick: function onClick() {
							return _this2.setState({ isDark: false });
						} },
					'Light'
				);
			} else {
				if (ch) {
					ch.href = 'css/light.css';
					flg.setAttribute('value', '');
				}
				return React.createElement(
					'button',
					{ onClick: function onClick() {
							return _this2.setState({ isDark: true });
						} },
					'dark'
				);
			}
		}
	}]);

	return DarkModeButton;
}(React.Component);

var domContainer = document.querySelector('#dark_mode_container');
ReactDOM.render(React.createElement(DarkModeButton, null), domContainer);