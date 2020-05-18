package utils;
import static java.awt.Color.BLACK;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class Multiplexer8To1 extends JPanel{

	private static final long serialVersionUID = -5479090601671002747L;
	private RenderingHints hints;
	private ZoomAndPanListener zoomAndPanListener;

	public Multiplexer8To1() {
		this.hints = new RenderingHints(null);
		this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		this.zoomAndPanListener = new ZoomAndPanListener(this);
		this.addMouseListener(zoomAndPanListener);
		this.addMouseMotionListener(zoomAndPanListener);
		this.addMouseWheelListener(zoomAndPanListener);
	}

	@Override
    public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
    	Graphics2D g = (Graphics2D)gg;
    	g.setTransform(zoomAndPanListener.getCoordTransform());
    	g.setRenderingHints(hints);
        Shape shape = null;
        
//    	Insets insets = getInsets();
//    	int w = (int) ((getWidth() - insets.left - insets.right) / 2);
//    	int h = (int) ((getHeight() - insets.top - insets.bottom) / 2);
//    	g.translate(w, h);
        
        @SuppressWarnings("unused")
		float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();
        
        // 

        // _0

        // _0_0

        // _0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(14.0, 56.0);
        ((GeneralPath) shape).lineTo(305.0, 55.0);
        ((GeneralPath) shape).lineTo(270.0, 120.0);
        ((GeneralPath) shape).lineTo(50.0, 120.0);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xECECEC));
        g.fill(shape);
        g.setPaint(BLACK);
        g.setStroke(new BasicStroke(3, 0, 0, 4));
        g.draw(shape);
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 6.559158f, 0));

        // _0_0_1

        // _0_0_1_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(232.1211, 56.328125);
        ((GeneralPath) shape).lineTo(232.1211, 26.328125);

        g.draw(shape);

        // _0_0_1_1
        // D1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(220.17773, 18.921875);
        ((GeneralPath) shape).lineTo(220.17773, 9.5234375);
        ((GeneralPath) shape).quadTo(219.41992, 9.515625, 219.05273, 9.1796875);
        ((GeneralPath) shape).quadTo(218.69336, 8.84375, 218.69336, 8.3203125);
        ((GeneralPath) shape).quadTo(218.69336, 7.78125, 219.06055, 7.4453125);
        ((GeneralPath) shape).quadTo(219.42773, 7.109375, 220.29492, 7.109375);
        ((GeneralPath) shape).lineTo(225.32617, 7.125);
        ((GeneralPath) shape).quadTo(226.49805, 7.125, 227.61523, 7.5625);
        ((GeneralPath) shape).quadTo(228.73242, 7.9921875, 229.41211, 8.65625);
        ((GeneralPath) shape).quadTo(229.92773, 9.1640625, 230.48242, 10.046875);
        ((GeneralPath) shape).quadTo(231.04492, 10.921875, 231.32617, 11.7890625);
        ((GeneralPath) shape).quadTo(231.60742, 12.65625, 231.60742, 13.90625);
        ((GeneralPath) shape).lineTo(231.60742, 15.0546875);
        ((GeneralPath) shape).quadTo(231.60742, 16.578125, 231.0918, 17.757812);
        ((GeneralPath) shape).quadTo(230.57617, 18.9375, 229.73242, 19.679688);
        ((GeneralPath) shape).quadTo(228.89648, 20.421875, 228.16211, 20.773438);
        ((GeneralPath) shape).quadTo(226.99023, 21.328125, 225.12305, 21.328125);
        ((GeneralPath) shape).lineTo(220.29492, 21.328125);
        ((GeneralPath) shape).quadTo(219.42773, 21.328125, 219.06055, 20.992188);
        ((GeneralPath) shape).quadTo(218.69336, 20.65625, 218.69336, 20.117188);
        ((GeneralPath) shape).quadTo(218.69336, 19.59375, 219.06055, 19.257812);
        ((GeneralPath) shape).quadTo(219.42773, 18.914062, 220.17773, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(222.58398, 18.921875);
        ((GeneralPath) shape).lineTo(225.13867, 18.921875);
        ((GeneralPath) shape).quadTo(226.56836, 18.921875, 227.29492, 18.515625);
        ((GeneralPath) shape).quadTo(228.24023, 17.976562, 228.7168, 17.148438);
        ((GeneralPath) shape).quadTo(229.20117, 16.3125, 229.20117, 14.984375);
        ((GeneralPath) shape).lineTo(229.20117, 13.8515625);
        ((GeneralPath) shape).quadTo(229.20117, 12.7109375, 228.77148, 11.859375);
        ((GeneralPath) shape).quadTo(228.0918, 10.5234375, 227.24023, 10.0234375);
        ((GeneralPath) shape).quadTo(226.38867, 9.5234375, 225.11523, 9.5234375);
        ((GeneralPath) shape).lineTo(222.58398, 9.5234375);
        ((GeneralPath) shape).lineTo(222.58398, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(241.18164, 5.7734375);
        ((GeneralPath) shape).lineTo(241.18164, 18.921875);
        ((GeneralPath) shape).lineTo(243.60352, 18.921875);
        ((GeneralPath) shape).quadTo(244.4707, 18.921875, 244.83789, 19.257812);
        ((GeneralPath) shape).quadTo(245.21289, 19.59375, 245.21289, 20.132812);
        ((GeneralPath) shape).quadTo(245.21289, 20.65625, 244.83789, 20.992188);
        ((GeneralPath) shape).quadTo(244.4707, 21.328125, 243.60352, 21.328125);
        ((GeneralPath) shape).lineTo(236.35352, 21.328125);
        ((GeneralPath) shape).quadTo(235.48633, 21.328125, 235.11133, 20.992188);
        ((GeneralPath) shape).quadTo(234.74414, 20.65625, 234.74414, 20.117188);
        ((GeneralPath) shape).quadTo(234.74414, 19.59375, 235.11133, 19.257812);
        ((GeneralPath) shape).quadTo(235.48633, 18.921875, 236.35352, 18.921875);
        ((GeneralPath) shape).lineTo(238.77539, 18.921875);
        ((GeneralPath) shape).lineTo(238.77539, 8.90625);
        ((GeneralPath) shape).lineTo(236.63477, 9.46875);
        ((GeneralPath) shape).quadTo(236.11914, 9.609375, 235.86133, 9.609375);
        ((GeneralPath) shape).quadTo(235.40039, 9.609375, 235.06445, 9.25);
        ((GeneralPath) shape).quadTo(234.73633, 8.8828125, 234.73633, 8.3515625);
        ((GeneralPath) shape).quadTo(234.73633, 7.875, 234.97852, 7.6015625);
        ((GeneralPath) shape).quadTo(235.22852, 7.3203125, 236.00977, 7.109375);
        ((GeneralPath) shape).lineTo(241.18164, 5.7734375);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_1_2
        // 1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(235.14627, 73.87838);
        ((GeneralPath) shape).lineTo(233.62283, 73.87838);
        ((GeneralPath) shape).lineTo(233.62283, 64.16788);
        ((GeneralPath) shape).quadTo(233.06987, 64.69261, 232.17838, 65.217354);
        ((GeneralPath) shape).quadTo(231.2869, 65.742096, 230.57596, 66.007286);
        ((GeneralPath) shape).lineTo(230.57596, 64.53463);
        ((GeneralPath) shape).quadTo(231.85112, 63.930897, 232.80469, 63.0789);
        ((GeneralPath) shape).quadTo(233.76389, 62.22126, 234.16449, 61.420044);
        ((GeneralPath) shape).lineTo(235.14627, 61.420044);
        ((GeneralPath) shape).lineTo(235.14627, 73.87838);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_1
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 126, 0));

        // _0_0_2

        // _0_0_2_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(144.0, 56.0);
        ((GeneralPath) shape).lineTo(144.0, 26.0);

        g.draw(shape);

        // _0_0_2_1
        // D0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(132.06836, 18.59375);
        ((GeneralPath) shape).lineTo(132.06836, 9.1953125);
        ((GeneralPath) shape).quadTo(131.31055, 9.1875, 130.94336, 8.8515625);
        ((GeneralPath) shape).quadTo(130.58398, 8.515625, 130.58398, 7.9921875);
        ((GeneralPath) shape).quadTo(130.58398, 7.453125, 130.95117, 7.1171875);
        ((GeneralPath) shape).quadTo(131.31836, 6.78125, 132.18555, 6.78125);
        ((GeneralPath) shape).lineTo(137.2168, 6.796875);
        ((GeneralPath) shape).quadTo(138.38867, 6.796875, 139.50586, 7.234375);
        ((GeneralPath) shape).quadTo(140.62305, 7.6640625, 141.30273, 8.328125);
        ((GeneralPath) shape).quadTo(141.81836, 8.8359375, 142.37305, 9.71875);
        ((GeneralPath) shape).quadTo(142.93555, 10.59375, 143.2168, 11.4609375);
        ((GeneralPath) shape).quadTo(143.49805, 12.328125, 143.49805, 13.578125);
        ((GeneralPath) shape).lineTo(143.49805, 14.7265625);
        ((GeneralPath) shape).quadTo(143.49805, 16.25, 142.98242, 17.429688);
        ((GeneralPath) shape).quadTo(142.4668, 18.609375, 141.62305, 19.351562);
        ((GeneralPath) shape).quadTo(140.78711, 20.09375, 140.05273, 20.445312);
        ((GeneralPath) shape).quadTo(138.88086, 21.0, 137.01367, 21.0);
        ((GeneralPath) shape).lineTo(132.18555, 21.0);
        ((GeneralPath) shape).quadTo(131.31836, 21.0, 130.95117, 20.664062);
        ((GeneralPath) shape).quadTo(130.58398, 20.328125, 130.58398, 19.789062);
        ((GeneralPath) shape).quadTo(130.58398, 19.265625, 130.95117, 18.929688);
        ((GeneralPath) shape).quadTo(131.31836, 18.585938, 132.06836, 18.59375);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(134.47461, 18.59375);
        ((GeneralPath) shape).lineTo(137.0293, 18.59375);
        ((GeneralPath) shape).quadTo(138.45898, 18.59375, 139.18555, 18.1875);
        ((GeneralPath) shape).quadTo(140.13086, 17.648438, 140.60742, 16.820312);
        ((GeneralPath) shape).quadTo(141.0918, 15.984375, 141.0918, 14.65625);
        ((GeneralPath) shape).lineTo(141.0918, 13.5234375);
        ((GeneralPath) shape).quadTo(141.0918, 12.3828125, 140.66211, 11.53125);
        ((GeneralPath) shape).quadTo(139.98242, 10.1953125, 139.13086, 9.6953125);
        ((GeneralPath) shape).quadTo(138.2793, 9.1953125, 137.00586, 9.1953125);
        ((GeneralPath) shape).lineTo(134.47461, 9.1953125);
        ((GeneralPath) shape).lineTo(134.47461, 18.59375);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(157.08789, 12.078125);
        ((GeneralPath) shape).lineTo(157.08789, 14.71875);
        ((GeneralPath) shape).quadTo(157.08789, 17.0625, 155.73633, 19.210938);
        ((GeneralPath) shape).quadTo(154.38477, 21.351562, 151.86133, 21.351562);
        ((GeneralPath) shape).quadTo(150.4082, 21.351562, 149.4082, 20.710938);
        ((GeneralPath) shape).quadTo(148.4082, 20.070312, 147.50977, 18.359375);
        ((GeneralPath) shape).quadTo(146.61133, 16.640625, 146.61133, 14.71875);
        ((GeneralPath) shape).lineTo(146.61133, 12.078125);
        ((GeneralPath) shape).quadTo(146.61133, 9.75, 147.95508, 7.6015625);
        ((GeneralPath) shape).quadTo(149.30664, 5.4453125, 151.83008, 5.4453125);
        ((GeneralPath) shape).quadTo(153.26758, 5.4453125, 154.26758, 6.078125);
        ((GeneralPath) shape).quadTo(155.27539, 6.7109375, 156.18164, 8.4296875);
        ((GeneralPath) shape).quadTo(157.08789, 10.1484375, 157.08789, 12.078125);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(154.67383, 12.078125);
        ((GeneralPath) shape).quadTo(154.67383, 10.125, 153.58789, 8.65625);
        ((GeneralPath) shape).quadTo(152.96289, 7.8515625, 151.83789, 7.8515625);
        ((GeneralPath) shape).quadTo(150.75195, 7.8515625, 150.05664, 8.7421875);
        ((GeneralPath) shape).quadTo(149.01758, 10.0859375, 149.01758, 12.078125);
        ((GeneralPath) shape).lineTo(149.01758, 14.71875);
        ((GeneralPath) shape).quadTo(149.01758, 16.6875, 150.11914, 18.140625);
        ((GeneralPath) shape).quadTo(150.72852, 18.945312, 151.86133, 18.945312);
        ((GeneralPath) shape).quadTo(152.93945, 18.945312, 153.63477, 18.054688);
        ((GeneralPath) shape).quadTo(154.67383, 16.710938, 154.67383, 14.71875);
        ((GeneralPath) shape).lineTo(154.67383, 12.078125);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_2_2
        // 0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(139.62016, 67.7447);
        ((GeneralPath) shape).quadTo(139.62016, 65.544174, 140.07155, 64.20694);
        ((GeneralPath) shape).quadTo(140.52293, 62.864056, 141.41443, 62.136192);
        ((GeneralPath) shape).quadTo(142.31157, 61.408325, 143.66573, 61.408325);
        ((GeneralPath) shape).quadTo(144.66443, 61.408325, 145.41486, 61.808933);
        ((GeneralPath) shape).quadTo(146.17094, 62.20954, 146.66182, 62.97126);
        ((GeneralPath) shape).quadTo(147.15271, 63.727337, 147.42918, 64.816315);
        ((GeneralPath) shape).quadTo(147.7113, 65.89964, 147.7113, 67.7447);
        ((GeneralPath) shape).quadTo(147.7113, 69.92829, 147.25992, 71.27117);
        ((GeneralPath) shape).quadTo(146.81416, 72.61406, 145.91704, 73.347565);
        ((GeneralPath) shape).quadTo(145.02554, 74.08107, 143.66573, 74.08107);
        ((GeneralPath) shape).quadTo(141.87146, 74.08107, 140.84454, 72.78897);
        ((GeneralPath) shape).quadTo(139.62016, 71.242966, 139.62016, 67.7447);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(141.18309, 67.7447);
        ((GeneralPath) shape).quadTo(141.18309, 70.80286, 141.89967, 71.81284);
        ((GeneralPath) shape).quadTo(142.61626, 72.82282, 143.66573, 72.82282);
        ((GeneralPath) shape).quadTo(144.71521, 72.82282, 145.42615, 71.8072);
        ((GeneralPath) shape).quadTo(146.14273, 70.79157, 146.14273, 67.7447);
        ((GeneralPath) shape).quadTo(146.14273, 64.68089, 145.42615, 63.676556);
        ((GeneralPath) shape).quadTo(144.71521, 62.666573, 143.6488, 62.666573);
        ((GeneralPath) shape).quadTo(142.59932, 62.666573, 141.97302, 63.558067);
        ((GeneralPath) shape).quadTo(141.18309, 64.692184, 141.18309, 67.7447);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_2

        // _0_0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(287.91916, 86.77051);
        ((GeneralPath) shape).lineTo(318.69174, 86.77051);

        g.setStroke(new BasicStroke(3.0000024f, 0, 0, 4));
        g.draw(shape);

        // _0_0_4
        // .
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(339.68976, 93.07064);
        ((GeneralPath) shape).quadTo(339.68976, 93.523766, 339.36685, 93.831055);
        ((GeneralPath) shape).quadTo(339.04913, 94.13834, 338.48663, 94.13834);
        ((GeneralPath) shape).quadTo(337.92413, 94.13834, 337.60123, 93.831055);
        ((GeneralPath) shape).quadTo(337.2835, 93.523766, 337.2835, 93.07064);
        ((GeneralPath) shape).quadTo(337.2835, 92.617516, 337.60123, 92.31543);
        ((GeneralPath) shape).quadTo(337.92413, 92.00814, 338.48663, 92.00814);
        ((GeneralPath) shape).quadTo(339.04913, 92.00814, 339.36685, 92.31543);
        ((GeneralPath) shape).quadTo(339.68976, 92.617516, 339.68976, 93.07064);
        ((GeneralPath) shape).closePath();

        g.fill(shape);
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 51, -1));

        // _0_0_5

        // _0_0_5_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(109.0, 122.0);
        ((GeneralPath) shape).lineTo(109.0, 147.0);

        g.setStroke(new BasicStroke(3, 0, 0, 4));
        g.draw(shape);

        // _0_0_5_1
        // S
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(106.23828, 165.39062);
        ((GeneralPath) shape).quadTo(105.91797, 165.76562, 105.72266, 165.875);
        ((GeneralPath) shape).quadTo(105.53516, 165.97656, 105.25391, 165.97656);
        ((GeneralPath) shape).quadTo(104.69922, 165.97656, 104.36328, 165.60938);
        ((GeneralPath) shape).quadTo(104.03516, 165.23438, 104.03516, 164.38281);
        ((GeneralPath) shape).lineTo(104.03516, 162.76562);
        ((GeneralPath) shape).quadTo(104.03516, 161.89844, 104.36328, 161.53125);
        ((GeneralPath) shape).quadTo(104.69922, 161.15625, 105.25391, 161.15625);
        ((GeneralPath) shape).quadTo(105.67578, 161.15625, 105.95703, 161.38281);
        ((GeneralPath) shape).quadTo(106.24609, 161.60156, 106.39453, 162.125);
        ((GeneralPath) shape).quadTo(106.55078, 162.64844, 106.71484, 162.83594);
        ((GeneralPath) shape).quadTo(107.05859, 163.19531, 107.92578, 163.57031);
        ((GeneralPath) shape).quadTo(108.79297, 163.94531, 109.82422, 163.94531);
        ((GeneralPath) shape).quadTo(111.42578, 163.94531, 112.45703, 163.19531);
        ((GeneralPath) shape).quadTo(113.11328, 162.74219, 113.11328, 162.07031);
        ((GeneralPath) shape).quadTo(113.11328, 161.625, 112.79297, 161.23438);
        ((GeneralPath) shape).quadTo(112.48047, 160.84375, 111.76953, 160.58594);
        ((GeneralPath) shape).quadTo(111.30078, 160.40625, 109.66797, 160.10156);
        ((GeneralPath) shape).quadTo(107.69922, 159.74219, 106.69141, 159.22656);
        ((GeneralPath) shape).quadTo(105.68359, 158.71094, 105.09766, 157.77344);
        ((GeneralPath) shape).quadTo(104.51172, 156.83594, 104.51172, 155.74219);
        ((GeneralPath) shape).quadTo(104.51172, 154.02344, 105.94922, 152.72656);
        ((GeneralPath) shape).quadTo(107.39453, 151.42969, 109.70703, 151.42969);
        ((GeneralPath) shape).quadTo(110.62891, 151.42969, 111.41797, 151.64062);
        ((GeneralPath) shape).quadTo(112.21484, 151.84375, 112.85547, 152.26562);
        ((GeneralPath) shape).quadTo(113.32422, 151.80469, 113.79297, 151.80469);
        ((GeneralPath) shape).quadTo(114.32422, 151.80469, 114.65234, 152.17969);
        ((GeneralPath) shape).quadTo(114.98828, 152.54688, 114.98828, 153.39844);
        ((GeneralPath) shape).lineTo(114.98828, 155.20312);
        ((GeneralPath) shape).quadTo(114.98828, 156.07031, 114.65234, 156.44531);
        ((GeneralPath) shape).quadTo(114.32422, 156.8125, 113.79297, 156.8125);
        ((GeneralPath) shape).quadTo(113.34766, 156.8125, 113.01953, 156.53906);
        ((GeneralPath) shape).quadTo(112.76172, 156.34375, 112.62891, 155.75);
        ((GeneralPath) shape).quadTo(112.50391, 155.14844, 112.30859, 154.89062);
        ((GeneralPath) shape).quadTo(111.96484, 154.44531, 111.28516, 154.14062);
        ((GeneralPath) shape).quadTo(110.60547, 153.83594, 109.71484, 153.83594);
        ((GeneralPath) shape).quadTo(108.41797, 153.83594, 107.66016, 154.4375);
        ((GeneralPath) shape).quadTo(106.90234, 155.03906, 106.90234, 155.69531);
        ((GeneralPath) shape).quadTo(106.90234, 156.14062, 107.21484, 156.5625);
        ((GeneralPath) shape).quadTo(107.52734, 156.97656, 108.12109, 157.21094);
        ((GeneralPath) shape).quadTo(108.51953, 157.375, 110.36328, 157.74219);
        ((GeneralPath) shape).quadTo(112.21484, 158.10156, 113.19922, 158.53906);
        ((GeneralPath) shape).quadTo(114.19141, 158.96875, 114.84766, 159.89844);
        ((GeneralPath) shape).quadTo(115.50391, 160.82031, 115.50391, 162.09375);
        ((GeneralPath) shape).quadTo(115.50391, 163.875, 114.25391, 164.94531);
        ((GeneralPath) shape).quadTo(112.58984, 166.35156, 110.01172, 166.35156);
        ((GeneralPath) shape).quadTo(109.01172, 166.35156, 108.06641, 166.10938);
        ((GeneralPath) shape).quadTo(107.12891, 165.86719, 106.23828, 165.39062);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_5

        // _0_0_6
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(276.93915, 106.0);
        ((GeneralPath) shape).lineTo(318.5606, 106.0);

        g.draw(shape);

        // _0_0_7
        // .
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(339.68976, 112.30013);
        ((GeneralPath) shape).quadTo(339.68976, 112.75326, 339.36685, 113.06055);
        ((GeneralPath) shape).quadTo(339.04913, 113.367836, 338.48663, 113.367836);
        ((GeneralPath) shape).quadTo(337.92413, 113.367836, 337.60123, 113.06055);
        ((GeneralPath) shape).quadTo(337.2835, 112.75326, 337.2835, 112.30013);
        ((GeneralPath) shape).quadTo(337.2835, 111.84701, 337.60123, 111.54492);
        ((GeneralPath) shape).quadTo(337.92413, 111.23763, 338.48663, 111.23763);
        ((GeneralPath) shape).quadTo(339.04913, 111.23763, 339.36685, 111.54492);
        ((GeneralPath) shape).quadTo(339.68976, 111.84701, 339.68976, 112.30013);
        ((GeneralPath) shape).closePath();

        g.fill(shape);
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 12.82143f, 0.175781f));

        // _0_0_8

        // _0_0_8_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(194.24219, 56.328125);
        ((GeneralPath) shape).lineTo(194.24219, 26.328125);

        g.draw(shape);

        // _0_0_8_1
        // D2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(182.41211, 18.921875);
        ((GeneralPath) shape).lineTo(182.41211, 9.5234375);
        ((GeneralPath) shape).quadTo(181.6543, 9.515625, 181.28711, 9.1796875);
        ((GeneralPath) shape).quadTo(180.92773, 8.84375, 180.92773, 8.3203125);
        ((GeneralPath) shape).quadTo(180.92773, 7.78125, 181.29492, 7.4453125);
        ((GeneralPath) shape).quadTo(181.66211, 7.109375, 182.5293, 7.109375);
        ((GeneralPath) shape).lineTo(187.56055, 7.125);
        ((GeneralPath) shape).quadTo(188.73242, 7.125, 189.84961, 7.5625);
        ((GeneralPath) shape).quadTo(190.9668, 7.9921875, 191.64648, 8.65625);
        ((GeneralPath) shape).quadTo(192.16211, 9.1640625, 192.7168, 10.046875);
        ((GeneralPath) shape).quadTo(193.2793, 10.921875, 193.56055, 11.7890625);
        ((GeneralPath) shape).quadTo(193.8418, 12.65625, 193.8418, 13.90625);
        ((GeneralPath) shape).lineTo(193.8418, 15.0546875);
        ((GeneralPath) shape).quadTo(193.8418, 16.578125, 193.32617, 17.757812);
        ((GeneralPath) shape).quadTo(192.81055, 18.9375, 191.9668, 19.679688);
        ((GeneralPath) shape).quadTo(191.13086, 20.421875, 190.39648, 20.773438);
        ((GeneralPath) shape).quadTo(189.22461, 21.328125, 187.35742, 21.328125);
        ((GeneralPath) shape).lineTo(182.5293, 21.328125);
        ((GeneralPath) shape).quadTo(181.66211, 21.328125, 181.29492, 20.992188);
        ((GeneralPath) shape).quadTo(180.92773, 20.65625, 180.92773, 20.117188);
        ((GeneralPath) shape).quadTo(180.92773, 19.59375, 181.29492, 19.257812);
        ((GeneralPath) shape).quadTo(181.66211, 18.914062, 182.41211, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(184.81836, 18.921875);
        ((GeneralPath) shape).lineTo(187.37305, 18.921875);
        ((GeneralPath) shape).quadTo(188.80273, 18.921875, 189.5293, 18.515625);
        ((GeneralPath) shape).quadTo(190.47461, 17.976562, 190.95117, 17.148438);
        ((GeneralPath) shape).quadTo(191.43555, 16.3125, 191.43555, 14.984375);
        ((GeneralPath) shape).lineTo(191.43555, 13.8515625);
        ((GeneralPath) shape).quadTo(191.43555, 12.7109375, 191.00586, 11.859375);
        ((GeneralPath) shape).quadTo(190.32617, 10.5234375, 189.47461, 10.0234375);
        ((GeneralPath) shape).quadTo(188.62305, 9.5234375, 187.34961, 9.5234375);
        ((GeneralPath) shape).lineTo(184.81836, 9.5234375);
        ((GeneralPath) shape).lineTo(184.81836, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(199.94727, 18.921875);
        ((GeneralPath) shape).lineTo(205.20508, 18.921875);
        ((GeneralPath) shape).quadTo(205.64258, 18.570312, 206.02539, 18.570312);
        ((GeneralPath) shape).quadTo(206.58008, 18.570312, 206.90039, 18.9375);
        ((GeneralPath) shape).quadTo(207.2207, 19.296875, 207.2207, 20.179688);
        ((GeneralPath) shape).lineTo(207.2207, 21.328125);
        ((GeneralPath) shape).lineTo(196.27539, 21.328125);
        ((GeneralPath) shape).lineTo(196.27539, 18.78125);
        ((GeneralPath) shape).quadTo(202.76758, 13.4296875, 204.11914, 11.890625);
        ((GeneralPath) shape).quadTo(204.79883, 11.109375, 204.79883, 10.40625);
        ((GeneralPath) shape).quadTo(204.79883, 9.5234375, 204.02539, 8.8515625);
        ((GeneralPath) shape).quadTo(203.25977, 8.1796875, 201.98633, 8.1796875);
        ((GeneralPath) shape).quadTo(200.69727, 8.1796875, 199.83789, 8.9296875);
        ((GeneralPath) shape).quadTo(199.39258, 9.328125, 199.12695, 10.109375);
        ((GeneralPath) shape).quadTo(198.96289, 10.6015625, 198.67383, 10.8125);
        ((GeneralPath) shape).quadTo(198.38477, 11.0234375, 197.96289, 11.0234375);
        ((GeneralPath) shape).quadTo(197.4707, 11.0234375, 197.11133, 10.671875);
        ((GeneralPath) shape).quadTo(196.75977, 10.3203125, 196.75977, 9.8515625);
        ((GeneralPath) shape).quadTo(196.75977, 9.1484375, 197.4082, 8.09375);
        ((GeneralPath) shape).quadTo(198.05664, 7.0390625, 199.30664, 6.40625);
        ((GeneralPath) shape).quadTo(200.55664, 5.7734375, 202.01758, 5.7734375);
        ((GeneralPath) shape).quadTo(204.29102, 5.7734375, 205.74414, 7.15625);
        ((GeneralPath) shape).quadTo(207.19727, 8.53125, 207.19727, 10.484375);
        ((GeneralPath) shape).quadTo(207.19727, 11.484375, 206.7832, 12.3359375);
        ((GeneralPath) shape).quadTo(206.36914, 13.1796875, 204.7832, 14.6796875);
        ((GeneralPath) shape).quadTo(203.54102, 15.8671875, 199.94727, 18.921875);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_8_2
        // 2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(198.40147, 72.41137);
        ((GeneralPath) shape).lineTo(198.40147, 73.87838);
        ((GeneralPath) shape).lineTo(190.19748, 73.87838);
        ((GeneralPath) shape).quadTo(190.18056, 73.32543, 190.37804, 72.81762);
        ((GeneralPath) shape).quadTo(190.68837, 71.982544, 191.37674, 71.170044);
        ((GeneralPath) shape).quadTo(192.07076, 70.357544, 193.37413, 69.29114);
        ((GeneralPath) shape).quadTo(195.3941, 67.632286, 196.10504, 66.6618);
        ((GeneralPath) shape).quadTo(196.81598, 65.691315, 196.81598, 64.82803);
        ((GeneralPath) shape).quadTo(196.81598, 63.925255, 196.1671, 63.304596);
        ((GeneralPath) shape).quadTo(195.52388, 62.67829, 194.48004, 62.67829);
        ((GeneralPath) shape).quadTo(193.37978, 62.67829, 192.71962, 63.338448);
        ((GeneralPath) shape).quadTo(192.05946, 63.998604, 192.05382, 65.16657);
        ((GeneralPath) shape).lineTo(190.48524, 65.00859);
        ((GeneralPath) shape).quadTo(190.64888, 63.253815, 191.69835, 62.339752);
        ((GeneralPath) shape).quadTo(192.74783, 61.420044, 194.51389, 61.420044);
        ((GeneralPath) shape).quadTo(196.30252, 61.420044, 197.34071, 62.4131);
        ((GeneralPath) shape).quadTo(198.38455, 63.400513, 198.38455, 64.861885);
        ((GeneralPath) shape).quadTo(198.38455, 65.606674, 198.07986, 66.3289);
        ((GeneralPath) shape).quadTo(197.77518, 67.04548, 197.06424, 67.84106);
        ((GeneralPath) shape).quadTo(196.35895, 68.63663, 194.71701, 70.02465);
        ((GeneralPath) shape).quadTo(193.34592, 71.17569, 192.9566, 71.58758);
        ((GeneralPath) shape).quadTo(192.56728, 71.99947, 192.31337, 72.41137);
        ((GeneralPath) shape).lineTo(198.40147, 72.41137);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_8
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, 19.5876f, -0.169922f));

        // _0_0_9

        // _0_0_9_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.36328, 56.328125);
        ((GeneralPath) shape).lineTo(156.36328, 26.328125);

        g.draw(shape);

        // _0_0_9_1
        // D3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(144.27539, 18.921875);
        ((GeneralPath) shape).lineTo(144.27539, 9.5234375);
        ((GeneralPath) shape).quadTo(143.51758, 9.515625, 143.15039, 9.1796875);
        ((GeneralPath) shape).quadTo(142.79102, 8.84375, 142.79102, 8.3203125);
        ((GeneralPath) shape).quadTo(142.79102, 7.78125, 143.1582, 7.4453125);
        ((GeneralPath) shape).quadTo(143.52539, 7.109375, 144.39258, 7.109375);
        ((GeneralPath) shape).lineTo(149.42383, 7.125);
        ((GeneralPath) shape).quadTo(150.5957, 7.125, 151.71289, 7.5625);
        ((GeneralPath) shape).quadTo(152.83008, 7.9921875, 153.50977, 8.65625);
        ((GeneralPath) shape).quadTo(154.02539, 9.1640625, 154.58008, 10.046875);
        ((GeneralPath) shape).quadTo(155.14258, 10.921875, 155.42383, 11.7890625);
        ((GeneralPath) shape).quadTo(155.70508, 12.65625, 155.70508, 13.90625);
        ((GeneralPath) shape).lineTo(155.70508, 15.0546875);
        ((GeneralPath) shape).quadTo(155.70508, 16.578125, 155.18945, 17.757812);
        ((GeneralPath) shape).quadTo(154.67383, 18.9375, 153.83008, 19.679688);
        ((GeneralPath) shape).quadTo(152.99414, 20.421875, 152.25977, 20.773438);
        ((GeneralPath) shape).quadTo(151.08789, 21.328125, 149.2207, 21.328125);
        ((GeneralPath) shape).lineTo(144.39258, 21.328125);
        ((GeneralPath) shape).quadTo(143.52539, 21.328125, 143.1582, 20.992188);
        ((GeneralPath) shape).quadTo(142.79102, 20.65625, 142.79102, 20.117188);
        ((GeneralPath) shape).quadTo(142.79102, 19.59375, 143.1582, 19.257812);
        ((GeneralPath) shape).quadTo(143.52539, 18.914062, 144.27539, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(146.68164, 18.921875);
        ((GeneralPath) shape).lineTo(149.23633, 18.921875);
        ((GeneralPath) shape).quadTo(150.66602, 18.921875, 151.39258, 18.515625);
        ((GeneralPath) shape).quadTo(152.33789, 17.976562, 152.81445, 17.148438);
        ((GeneralPath) shape).quadTo(153.29883, 16.3125, 153.29883, 14.984375);
        ((GeneralPath) shape).lineTo(153.29883, 13.8515625);
        ((GeneralPath) shape).quadTo(153.29883, 12.7109375, 152.86914, 11.859375);
        ((GeneralPath) shape).quadTo(152.18945, 10.5234375, 151.33789, 10.0234375);
        ((GeneralPath) shape).quadTo(150.48633, 9.5234375, 149.21289, 9.5234375);
        ((GeneralPath) shape).lineTo(146.68164, 9.5234375);
        ((GeneralPath) shape).lineTo(146.68164, 18.921875);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(167.48242, 13.296875);
        ((GeneralPath) shape).quadTo(168.54492, 14.0234375, 169.06836, 14.984375);
        ((GeneralPath) shape).quadTo(169.59961, 15.9375, 169.59961, 17.117188);
        ((GeneralPath) shape).quadTo(169.59961, 18.398438, 168.9043, 19.484375);
        ((GeneralPath) shape).quadTo(168.20898, 20.5625, 166.88086, 21.125);
        ((GeneralPath) shape).quadTo(165.56055, 21.679688, 163.41211, 21.679688);
        ((GeneralPath) shape).quadTo(160.62305, 21.679688, 159.2168, 20.8125);
        ((GeneralPath) shape).quadTo(158.43555, 20.320312, 158.43555, 19.640625);
        ((GeneralPath) shape).quadTo(158.43555, 19.125, 158.7793, 18.765625);
        ((GeneralPath) shape).quadTo(159.12305, 18.40625, 159.61523, 18.40625);
        ((GeneralPath) shape).quadTo(159.95898, 18.40625, 160.31836, 18.640625);
        ((GeneralPath) shape).quadTo(160.83398, 18.992188, 161.29492, 19.109375);
        ((GeneralPath) shape).quadTo(161.99805, 19.273438, 163.22461, 19.273438);
        ((GeneralPath) shape).quadTo(165.32617, 19.273438, 166.26367, 18.734375);
        ((GeneralPath) shape).quadTo(167.20117, 18.1875, 167.20117, 17.1875);
        ((GeneralPath) shape).quadTo(167.20117, 16.453125, 166.6543, 15.765625);
        ((GeneralPath) shape).quadTo(166.10742, 15.078125, 165.19336, 14.703125);
        ((GeneralPath) shape).quadTo(164.66992, 14.4921875, 163.41211, 14.4140625);
        ((GeneralPath) shape).quadTo(162.81836, 14.375, 162.49023, 14.046875);
        ((GeneralPath) shape).quadTo(162.16992, 13.7109375, 162.16992, 13.21875);
        ((GeneralPath) shape).quadTo(162.16992, 12.703125, 162.52148, 12.3515625);
        ((GeneralPath) shape).quadTo(162.87305, 12.0, 163.41211, 12.0);
        ((GeneralPath) shape).quadTo(164.60742, 12.0, 164.69336, 11.984375);
        ((GeneralPath) shape).quadTo(165.16211, 11.9375, 165.63086, 11.6796875);
        ((GeneralPath) shape).quadTo(166.09961, 11.421875, 166.38086, 10.9765625);
        ((GeneralPath) shape).quadTo(166.66992, 10.5234375, 166.66992, 10.0390625);
        ((GeneralPath) shape).quadTo(166.66992, 9.28125, 165.99805, 8.7265625);
        ((GeneralPath) shape).quadTo(165.32617, 8.1640625, 163.97461, 8.1640625);
        ((GeneralPath) shape).quadTo(162.24023, 8.1640625, 161.32617, 9.171875);
        ((GeneralPath) shape).quadTo(161.06055, 9.46875, 160.89648, 9.546875);
        ((GeneralPath) shape).quadTo(160.64648, 9.6796875, 160.3418, 9.6796875);
        ((GeneralPath) shape).quadTo(159.84961, 9.6796875, 159.49805, 9.328125);
        ((GeneralPath) shape).quadTo(159.14648, 8.9765625, 159.14648, 8.4609375);
        ((GeneralPath) shape).quadTo(159.14648, 7.6484375, 160.22461, 6.890625);
        ((GeneralPath) shape).quadTo(161.79492, 5.7734375, 164.1543, 5.7734375);
        ((GeneralPath) shape).quadTo(166.38086, 5.7734375, 167.72461, 7.0390625);
        ((GeneralPath) shape).quadTo(169.07617, 8.3046875, 169.07617, 10.0859375);
        ((GeneralPath) shape).quadTo(169.07617, 11.015625, 168.67773, 11.8203125);
        ((GeneralPath) shape).quadTo(168.2793, 12.6171875, 167.48242, 13.296875);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_9_2
        // 3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(152.45963, 69.18595);
        ((GeneralPath) shape).lineTo(153.98306, 68.98283);
        ((GeneralPath) shape).quadTo(154.24826, 70.28057, 154.87456, 70.85045);
        ((GeneralPath) shape).quadTo(155.5065, 71.42033, 156.41493, 71.42033);
        ((GeneralPath) shape).quadTo(157.48697, 71.42033, 158.22612, 70.67554);
        ((GeneralPath) shape).quadTo(158.97092, 69.93075, 158.97092, 68.83048);
        ((GeneralPath) shape).quadTo(158.97092, 67.781006, 158.28255, 67.10392);
        ((GeneralPath) shape).quadTo(157.59982, 66.421196, 156.53906, 66.421196);
        ((GeneralPath) shape).quadTo(156.11024, 66.421196, 155.46701, 66.59047);
        ((GeneralPath) shape).lineTo(155.63628, 65.25323);
        ((GeneralPath) shape).quadTo(155.78862, 65.27016, 155.8789, 65.27016);
        ((GeneralPath) shape).quadTo(156.85503, 65.27016, 157.63368, 64.762344);
        ((GeneralPath) shape).quadTo(158.41232, 64.25453, 158.41232, 63.193764);
        ((GeneralPath) shape).quadTo(158.41232, 62.358696, 157.84244, 61.811386);
        ((GeneralPath) shape).quadTo(157.27821, 61.258434, 156.38107, 61.258434);
        ((GeneralPath) shape).quadTo(155.48958, 61.258434, 154.89713, 61.817028);
        ((GeneralPath) shape).quadTo(154.30469, 62.37562, 154.1354, 63.49281);
        ((GeneralPath) shape).lineTo(152.61197, 63.221977);
        ((GeneralPath) shape).quadTo(152.89409, 61.687256, 153.8815, 60.846542);
        ((GeneralPath) shape).quadTo(154.87456, 60.00583, 156.34721, 60.00583);
        ((GeneralPath) shape).quadTo(157.36284, 60.00583, 158.21484, 60.440292);
        ((GeneralPath) shape).quadTo(159.07248, 60.874756, 159.52386, 61.63083);
        ((GeneralPath) shape).quadTo(159.97525, 62.381264, 159.97525, 63.22762);
        ((GeneralPath) shape).quadTo(159.97525, 64.03448, 159.54079, 64.69463);
        ((GeneralPath) shape).quadTo(159.11197, 65.35479, 158.26562, 65.74411);
        ((GeneralPath) shape).quadTo(159.36588, 65.99802, 159.97525, 66.79923);
        ((GeneralPath) shape).quadTo(160.58463, 67.59481, 160.58463, 68.79663);
        ((GeneralPath) shape).quadTo(160.58463, 70.42163, 159.39973, 71.55575);
        ((GeneralPath) shape).quadTo(158.21484, 72.68422, 156.40364, 72.68422);
        ((GeneralPath) shape).quadTo(154.773, 72.68422, 153.68967, 71.71373);
        ((GeneralPath) shape).quadTo(152.61197, 70.7376, 152.45963, 69.18595);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_9
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, -11.90012f, -0.169922f));

        // _0_0_10

        // _0_0_10_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.36328, 56.328125);
        ((GeneralPath) shape).lineTo(156.36328, 26.328125);

        g.draw(shape);

        g.setTransform(transformations.poll()); // _0_0_10
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, -43.38785f, -0.169922f));

        // _0_0_11

        // _0_0_11_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.36328, 56.328125);
        ((GeneralPath) shape).lineTo(156.36328, 26.328125);

        g.draw(shape);

        // _0_0_11_1
        // ...
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(149.37695, 78.828125);
        ((GeneralPath) shape).lineTo(149.37695, 76.421875);
        ((GeneralPath) shape).lineTo(151.7832, 76.421875);
        ((GeneralPath) shape).lineTo(151.7832, 78.828125);
        ((GeneralPath) shape).lineTo(149.37695, 78.828125);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(156.04492, 78.828125);
        ((GeneralPath) shape).lineTo(156.04492, 76.421875);
        ((GeneralPath) shape).lineTo(158.45117, 76.421875);
        ((GeneralPath) shape).lineTo(158.45117, 78.828125);
        ((GeneralPath) shape).lineTo(156.04492, 78.828125);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(162.71289, 78.828125);
        ((GeneralPath) shape).lineTo(162.71289, 76.421875);
        ((GeneralPath) shape).lineTo(165.11914, 76.421875);
        ((GeneralPath) shape).lineTo(165.11914, 78.828125);
        ((GeneralPath) shape).lineTo(162.71289, 78.828125);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_11
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, -74.87557f, -0.169922f));

        // _0_0_12

        // _0_0_12_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.36328, 56.328125);
        ((GeneralPath) shape).lineTo(156.36328, 26.328125);

        g.draw(shape);

        g.setTransform(transformations.poll()); // _0_0_12
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1, 0, 0, 1, -106.3633f, -0.169922f));

        // _0_0_13

        // _0_0_13_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(156.36328, 56.328125);
        ((GeneralPath) shape).lineTo(156.36328, 26.328125);

        g.draw(shape);

        g.setTransform(transformations.poll()); // _0_0_13

        // _0_0_14
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.16696, 67.541016);
        ((GeneralPath) shape).lineTo(318.787, 67.541016);

        g.draw(shape);
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(0.944028f, 0, 0, 1.059291f, 0, 0));

        // _0_0_15
        // A0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(354.28778, 66.817894);
        ((GeneralPath) shape).lineTo(348.7761, 66.817894);
        ((GeneralPath) shape).lineTo(348.2227, 68.19767);
        ((GeneralPath) shape).lineTo(348.7761, 68.19767);
        ((GeneralPath) shape).quadTo(349.5951, 68.19767, 349.94186, 68.514946);
        ((GeneralPath) shape).quadTo(350.28867, 68.832214, 350.28867, 69.34133);
        ((GeneralPath) shape).quadTo(350.28867, 69.835686, 349.94186, 70.15296);
        ((GeneralPath) shape).quadTo(349.5951, 70.47024, 348.7761, 70.47024);
        ((GeneralPath) shape).lineTo(345.7214, 70.47024);
        ((GeneralPath) shape).quadTo(344.90237, 70.47024, 344.5556, 70.15296);
        ((GeneralPath) shape).quadTo(344.20142, 69.835686, 344.20142, 69.32658);
        ((GeneralPath) shape).quadTo(344.20142, 68.81746, 344.57034, 68.50018);
        ((GeneralPath) shape).quadTo(344.9319, 68.17554, 345.77304, 68.19767);
        ((GeneralPath) shape).lineTo(349.33685, 59.32137);
        ((GeneralPath) shape).lineTo(347.85376, 59.32137);
        ((GeneralPath) shape).quadTo(347.03476, 59.32137, 346.68796, 59.011475);
        ((GeneralPath) shape).quadTo(346.3412, 58.6942, 346.3412, 58.185085);
        ((GeneralPath) shape).quadTo(346.3412, 57.67597, 346.68796, 57.358696);
        ((GeneralPath) shape).quadTo(347.03476, 57.04142, 347.85376, 57.04142);
        ((GeneralPath) shape).lineTo(352.76044, 57.05618);
        ((GeneralPath) shape).lineTo(357.27606, 68.19767);
        ((GeneralPath) shape).quadTo(358.07294, 68.19767, 358.32382, 68.374756);
        ((GeneralPath) shape).quadTo(358.83295, 68.743675, 358.83295, 69.34133);
        ((GeneralPath) shape).quadTo(358.83295, 69.835686, 358.48615, 70.15296);
        ((GeneralPath) shape).quadTo(358.14673, 70.47024, 357.32773, 70.47024);
        ((GeneralPath) shape).lineTo(354.27304, 70.47024);
        ((GeneralPath) shape).quadTo(353.45404, 70.47024, 353.10724, 70.15296);
        ((GeneralPath) shape).quadTo(352.76044, 69.835686, 352.76044, 69.32658);
        ((GeneralPath) shape).quadTo(352.76044, 68.832214, 353.10724, 68.514946);
        ((GeneralPath) shape).quadTo(353.45404, 68.19767, 354.27304, 68.19767);
        ((GeneralPath) shape).lineTo(354.82642, 68.19767);
        ((GeneralPath) shape).lineTo(354.28778, 66.817894);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(353.34335, 64.54533);
        ((GeneralPath) shape).lineTo(351.52087, 60.051838);
        ((GeneralPath) shape).lineTo(349.68362, 64.54533);
        ((GeneralPath) shape).lineTo(353.34335, 64.54533);
        ((GeneralPath) shape).closePath();

        g.fill(shape);
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(366.14523, 64.85524);
        ((GeneralPath) shape).lineTo(366.14523, 66.51712);
        ((GeneralPath) shape).quadTo(366.14523, 67.992165, 365.29462, 69.344284);
        ((GeneralPath) shape).quadTo(364.444, 70.6915, 362.85587, 70.6915);
        ((GeneralPath) shape).quadTo(361.94135, 70.6915, 361.312, 70.288315);
        ((GeneralPath) shape).quadTo(360.68265, 69.88514, 360.11722, 68.80836);
        ((GeneralPath) shape).quadTo(359.5518, 67.726654, 359.5518, 66.51712);
        ((GeneralPath) shape).lineTo(359.5518, 64.85524);
        ((GeneralPath) shape).quadTo(359.5518, 63.390026, 360.39746, 62.037903);
        ((GeneralPath) shape).quadTo(361.24808, 60.680862, 362.8362, 60.680862);
        ((GeneralPath) shape).quadTo(363.7409, 60.680862, 364.37027, 61.079124);
        ((GeneralPath) shape).quadTo(365.00452, 61.477386, 365.5749, 62.559086);
        ((GeneralPath) shape).quadTo(366.14523, 63.640785, 366.14523, 64.85524);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(364.62595, 64.85524);
        ((GeneralPath) shape).quadTo(364.62595, 63.626034, 363.9425, 62.70167);
        ((GeneralPath) shape).quadTo(363.54916, 62.19524, 362.84113, 62.19524);
        ((GeneralPath) shape).quadTo(362.15768, 62.19524, 361.7201, 62.755756);
        ((GeneralPath) shape).quadTo(361.06616, 63.601448, 361.06616, 64.85524);
        ((GeneralPath) shape).lineTo(361.06616, 66.51712);
        ((GeneralPath) shape).quadTo(361.06616, 67.75616, 361.75943, 68.670685);
        ((GeneralPath) shape).quadTo(362.14294, 69.17712, 362.85587, 69.17712);
        ((GeneralPath) shape).quadTo(363.5344, 69.17712, 363.972, 68.6166);
        ((GeneralPath) shape).quadTo(364.62595, 67.770905, 364.62595, 66.51712);
        ((GeneralPath) shape).lineTo(364.62595, 64.85524);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_15

        // _0_0_16
        // .
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(338.8653, 128.32834);
        ((GeneralPath) shape).quadTo(338.8653, 128.78146, 338.5424, 129.08876);
        ((GeneralPath) shape).quadTo(338.22467, 129.39606, 337.66217, 129.39606);
        ((GeneralPath) shape).quadTo(337.09967, 129.39606, 336.77676, 129.08876);
        ((GeneralPath) shape).quadTo(336.45905, 128.78146, 336.45905, 128.32834);
        ((GeneralPath) shape).quadTo(336.45905, 127.87522, 336.77676, 127.573135);
        ((GeneralPath) shape).quadTo(337.09967, 127.26585, 337.66217, 127.26585);
        ((GeneralPath) shape).quadTo(338.22467, 127.26585, 338.5424, 127.573135);
        ((GeneralPath) shape).quadTo(338.8653, 127.87522, 338.8653, 128.32834);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        // _0_0_17
        // MUX
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(132.3143, 87.64472);
        ((GeneralPath) shape).lineTo(132.3143, 99.57181);
        ((GeneralPath) shape).lineTo(133.75961, 99.57181);
        ((GeneralPath) shape).quadTo(135.20493, 99.57181, 135.81691, 100.1317);
        ((GeneralPath) shape).quadTo(136.44191, 100.6916, 136.44191, 101.590034);
        ((GeneralPath) shape).quadTo(136.44191, 102.46243, 135.81691, 103.02232);
        ((GeneralPath) shape).quadTo(135.20493, 103.58222, 133.75961, 103.58222);
        ((GeneralPath) shape).lineTo(128.51222, 103.58222);
        ((GeneralPath) shape).quadTo(127.0669, 103.58222, 126.454926, 103.02232);
        ((GeneralPath) shape).quadTo(125.829926, 102.46243, 125.829926, 101.563995);
        ((GeneralPath) shape).quadTo(125.829926, 100.6916, 126.4419, 100.1317);
        ((GeneralPath) shape).quadTo(127.04086, 99.57181, 128.31691, 99.57181);
        ((GeneralPath) shape).lineTo(128.31691, 83.907745);
        ((GeneralPath) shape).quadTo(127.262215, 83.8166, 126.71534, 83.26972);
        ((GeneralPath) shape).quadTo(126.18149, 82.72285, 126.18149, 81.902534);
        ((GeneralPath) shape).quadTo(126.18149, 81.0041, 126.793465, 80.4442);
        ((GeneralPath) shape).quadTo(127.418465, 79.88431, 128.86378, 79.88431);
        ((GeneralPath) shape).lineTo(133.2518, 79.91035);
        ((GeneralPath) shape).lineTo(138.70753, 91.95462);
        ((GeneralPath) shape).lineTo(144.0591, 79.88431);
        ((GeneralPath) shape).lineTo(148.48618, 79.91035);
        ((GeneralPath) shape).quadTo(149.93149, 79.91035, 150.54347, 80.45722);
        ((GeneralPath) shape).quadTo(151.16847, 81.0041, 151.16847, 81.902534);
        ((GeneralPath) shape).quadTo(151.16847, 82.72285, 150.6216, 83.26972);
        ((GeneralPath) shape).quadTo(150.07472, 83.8166, 149.03305, 83.907745);
        ((GeneralPath) shape).lineTo(149.03305, 99.57181);
        ((GeneralPath) shape).quadTo(150.3091, 99.57181, 150.90805, 100.14472);
        ((GeneralPath) shape).quadTo(151.52003, 100.70462, 151.52003, 101.590034);
        ((GeneralPath) shape).quadTo(151.52003, 102.46243, 150.89503, 103.02232);
        ((GeneralPath) shape).quadTo(150.28305, 103.58222, 148.83774, 103.58222);
        ((GeneralPath) shape).lineTo(143.5643, 103.58222);
        ((GeneralPath) shape).quadTo(142.14503, 103.58222, 141.52003, 103.02232);
        ((GeneralPath) shape).quadTo(140.90805, 102.46243, 140.90805, 101.563995);
        ((GeneralPath) shape).quadTo(140.90805, 100.6916, 141.52003, 100.1317);
        ((GeneralPath) shape).quadTo(142.14503, 99.57181, 143.5643, 99.57181);
        ((GeneralPath) shape).lineTo(145.03566, 99.57181);
        ((GeneralPath) shape).lineTo(145.03566, 87.64472);
        ((GeneralPath) shape).lineTo(140.4393, 97.95722);
        ((GeneralPath) shape).lineTo(136.96274, 97.95722);
        ((GeneralPath) shape).lineTo(132.3143, 87.64472);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(171.73488, 83.907745);
        ((GeneralPath) shape).lineTo(171.73488, 95.26191);
        ((GeneralPath) shape).quadTo(171.73488, 98.97285, 169.0656, 101.57701);
        ((GeneralPath) shape).quadTo(166.39633, 104.16816, 162.64633, 104.16816);
        ((GeneralPath) shape).quadTo(160.79738, 104.16816, 159.15675, 103.51712);
        ((GeneralPath) shape).quadTo(157.52914, 102.85306, 156.22707, 101.590034);
        ((GeneralPath) shape).quadTo(154.938, 100.313995, 154.20883, 98.94681);
        ((GeneralPath) shape).quadTo(153.49269, 97.5666, 153.49269, 95.26191);
        ((GeneralPath) shape).lineTo(153.49269, 83.907745);
        ((GeneralPath) shape).quadTo(152.21664, 83.907745, 151.60466, 83.34785);
        ((GeneralPath) shape).quadTo(151.0057, 82.77493, 151.0057, 81.902534);
        ((GeneralPath) shape).quadTo(151.0057, 81.0041, 151.60466, 80.45722);
        ((GeneralPath) shape).quadTo(152.21664, 79.91035, 153.688, 79.91035);
        ((GeneralPath) shape).lineTo(158.9354, 79.88431);
        ((GeneralPath) shape).quadTo(160.3807, 79.88431, 160.99269, 80.4442);
        ((GeneralPath) shape).quadTo(161.61769, 81.0041, 161.61769, 81.902534);
        ((GeneralPath) shape).quadTo(161.61769, 82.80097, 160.99269, 83.36087);
        ((GeneralPath) shape).quadTo(160.3807, 83.907745, 158.9354, 83.907745);
        ((GeneralPath) shape).lineTo(157.49008, 83.907745);
        ((GeneralPath) shape).lineTo(157.49008, 95.61347);
        ((GeneralPath) shape).quadTo(157.49008, 97.41035, 158.98747, 98.79056);
        ((GeneralPath) shape).quadTo(160.48488, 100.157745, 162.59425, 100.157745);
        ((GeneralPath) shape).quadTo(164.0005, 100.157745, 165.22446, 99.49368);
        ((GeneralPath) shape).quadTo(166.46144, 98.82962, 167.24269, 97.5666);
        ((GeneralPath) shape).quadTo(167.72446, 96.75931, 167.72446, 95.61347);
        ((GeneralPath) shape).lineTo(167.72446, 83.907745);
        ((GeneralPath) shape).lineTo(166.27914, 83.907745);
        ((GeneralPath) shape).quadTo(164.83383, 83.907745, 164.22185, 83.36087);
        ((GeneralPath) shape).quadTo(163.60988, 82.80097, 163.60988, 81.902534);
        ((GeneralPath) shape).quadTo(163.60988, 81.0041, 164.22185, 80.4442);
        ((GeneralPath) shape).quadTo(164.83383, 79.88431, 166.27914, 79.88431);
        ((GeneralPath) shape).lineTo(171.53957, 79.91035);
        ((GeneralPath) shape).quadTo(172.98488, 79.91035, 173.59685, 80.45722);
        ((GeneralPath) shape).quadTo(174.20883, 81.0041, 174.20883, 81.902534);
        ((GeneralPath) shape).quadTo(174.20883, 82.77493, 173.59685, 83.34785);
        ((GeneralPath) shape).quadTo(172.9979, 83.907745, 171.73488, 83.907745);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(189.21535, 91.51191);
        ((GeneralPath) shape).lineTo(195.89503, 99.57181);
        ((GeneralPath) shape).quadTo(197.07993, 99.59785, 197.67888, 100.157745);
        ((GeneralPath) shape).quadTo(198.27785, 100.70462, 198.27785, 101.590034);
        ((GeneralPath) shape).quadTo(198.27785, 102.46243, 197.65285, 103.02232);
        ((GeneralPath) shape).quadTo(197.04086, 103.58222, 195.59555, 103.58222);
        ((GeneralPath) shape).lineTo(191.09035, 103.58222);
        ((GeneralPath) shape).quadTo(189.65805, 103.58222, 189.04607, 103.02232);
        ((GeneralPath) shape).quadTo(188.4341, 102.46243, 188.4341, 101.563995);
        ((GeneralPath) shape).quadTo(188.4341, 100.73066, 188.99399, 100.17076);
        ((GeneralPath) shape).quadTo(189.56691, 99.61087, 190.69972, 99.57181);
        ((GeneralPath) shape).lineTo(186.67628, 94.61087);
        ((GeneralPath) shape).lineTo(182.61378, 99.57181);
        ((GeneralPath) shape).quadTo(183.72055, 99.61087, 184.28044, 100.183784);
        ((GeneralPath) shape).quadTo(184.85336, 100.74368, 184.85336, 101.590034);
        ((GeneralPath) shape).quadTo(184.85336, 102.46243, 184.24138, 103.02232);
        ((GeneralPath) shape).quadTo(183.62941, 103.58222, 182.1841, 103.58222);
        ((GeneralPath) shape).lineTo(177.69191, 103.58222);
        ((GeneralPath) shape).quadTo(176.2466, 103.58222, 175.6216, 103.02232);
        ((GeneralPath) shape).quadTo(175.00961, 102.46243, 175.00961, 101.563995);
        ((GeneralPath) shape).quadTo(175.00961, 100.70462, 175.60857, 100.14472);
        ((GeneralPath) shape).quadTo(176.22055, 99.57181, 177.39243, 99.57181);
        ((GeneralPath) shape).lineTo(184.03305, 91.47285);
        ((GeneralPath) shape).lineTo(177.70493, 83.907745);
        ((GeneralPath) shape).quadTo(176.53305, 83.86868, 175.96013, 83.32181);
        ((GeneralPath) shape).quadTo(175.38722, 82.76191, 175.38722, 81.902534);
        ((GeneralPath) shape).quadTo(175.38722, 81.0041, 175.99919, 80.4442);
        ((GeneralPath) shape).quadTo(176.61118, 79.88431, 178.05649, 79.88431);
        ((GeneralPath) shape).lineTo(181.75441, 79.91035);
        ((GeneralPath) shape).quadTo(183.19972, 79.91035, 183.81169, 80.45722);
        ((GeneralPath) shape).quadTo(184.42368, 81.0041, 184.42368, 81.902534);
        ((GeneralPath) shape).quadTo(184.42368, 82.5666, 184.03305, 83.11347);
        ((GeneralPath) shape).quadTo(183.78566, 83.438995, 182.90024, 83.907745);
        ((GeneralPath) shape).lineTo(186.63722, 88.38691);
        ((GeneralPath) shape).lineTo(190.28305, 83.907745);
        ((GeneralPath) shape).quadTo(189.41066, 83.438995, 189.15024, 83.11347);
        ((GeneralPath) shape).quadTo(188.75961, 82.5666, 188.75961, 81.902534);
        ((GeneralPath) shape).quadTo(188.75961, 81.01712, 189.3716, 80.45722);
        ((GeneralPath) shape).quadTo(189.9966, 79.88431, 191.44191, 79.88431);
        ((GeneralPath) shape).lineTo(195.11378, 79.91035);
        ((GeneralPath) shape).quadTo(196.5591, 79.91035, 197.17107, 80.45722);
        ((GeneralPath) shape).quadTo(197.78305, 81.0041, 197.78305, 81.902534);
        ((GeneralPath) shape).quadTo(197.78305, 82.76191, 197.1841, 83.33482);
        ((GeneralPath) shape).quadTo(196.59816, 83.89472, 195.46535, 83.907745);
        ((GeneralPath) shape).lineTo(189.21535, 91.51191);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 12;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 6;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 335;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 160;
    }
}

