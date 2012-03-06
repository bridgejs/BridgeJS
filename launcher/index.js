new Ext.Application({
	launch: function() {
		new Ext.Panel({
			fullscreen: true,
			dockedItems: [{xtype:'toolbar', title: 'App Launcher'}],
			styleHtmlContent: true,
			items: [
			{
				xtype: "button",
				ui: "normal",
				text: "Normal"
			},
			{
				xtype: "button",
				ui: "round",
				text: "Round"
			}
				
			]
		});
	}
});
