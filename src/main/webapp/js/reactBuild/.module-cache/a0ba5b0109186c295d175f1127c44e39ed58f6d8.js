ReactDOM.render(
  React.createElement(DemoEditor, {placeholder: "请输入文本", id: "richEditor", submitButtonName: "提交", cancelButtonName: "清空"}),
  document.getElementById('example')
);