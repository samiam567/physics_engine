<?php



/**

 * The plugin bootstrap file

 *

 * This file is read by WordPress to generate the plugin information in the plugin

 * Dashboard. This file also includes all of the dependencies used by the plugin,

 * registers the activation and deactivation functions, and defines a function

 * this starts the plugin.

 *

 * @link:       http://class-advisor.com/wp-content/plugins/random-widgets-widget

 * @since             1.0.0

 * @package           Random_Widgets_Widget

 *

 * @wordpress-plugin

 * Plugin Name:       Random Widgets Widget

 * Plugin URI:        http://class-advisor.com/wp-content/plugins/random-widgets-widget

 * Description:        Displays a random widget from a set of choices

 * Version:           1.0.0

 * Author:            Alec P

 * Author URI:        http://class-advisor.com

 * License:            GPL-2.0+

 * License URI:        http://www.gnu.org/licenses/gpl-2.0.txt

 * Text Domain:            random-widgets-widget

 * Domain Path:            /languages

 */



// If this file is called directly, abort.

if ( ! defined( 'WPINC' ) ) {

	die;

}



/**

 * The code that runs during plugin activation.

 */

require_once plugin_dir_path( __FILE__ ) . 'includes/class-random-widgets-widget-activator.php';



/**

 * The code that runs during plugin deactivation.

 */

require_once plugin_dir_path( __FILE__ ) . 'includes/class-random-widgets-widget-deactivator.php';



/** This action is documented in includes/class-random-widgets-widget-activator.php */

register_activation_hook( __FILE__, array( 'Random_Widgets_Widget_Activator', 'activate' ) );



/** This action is documented in includes/class-random-widgets-widget-deactivator.php */

register_activation_hook( __FILE__, array( 'Random_Widgets_Widget_Deactivator', 'deactivate' ) );



/**

 * The core plugin class that is used to define internationalization,

 * dashboard-specific hooks, and public-facing site hooks.

 */

require_once plugin_dir_path( __FILE__ ) . 'includes/class-random-widgets-widget.php';



/**

 * Begins execution of the plugin.

 *

 * Since everything within the plugin is registered via hooks,

 * then kicking off the plugin from this point in the file does

 * not affect the page life cycle.

 *

 * @since    1.0.0

 */

function run_Random_Widgets_Widget() {



	$plugin = new Random_Widgets_Widget();

	$plugin->run();

///Start

class RandWidsWid extends WP_Widget {

	/**
	 * Sets up the widgets name etc
	 */
	public function __construct() {
		$widget_ops = array( 
			'classname' => 'Display Random Widgets Widget',
			'description' => 'Displays a random widget from a list by shortcode with a widget.',
			
		);

		parent::__construct( 'RandWidsWid', 'Random Widgets Widget', $widget_ops );
	}

	/**
	 * Outputs the content of the widget
	 *
	 * @param array $args
	 * @param array $instance
	 * 
	 */
	public function widget( $args, $instance ) {
			/* //php version
			//create the content of the widget
			$geah_rand_wids_wid_wid_ops = array('[do_widget id=recent-posts-7]','[do_widget id=text-44]','[do_widget id=yop_poll_widget-5]','[do_widget id=text-43]');

			$geah_rand_wids_wid_wid_ops_key = random_int( 0, count($geah_rand_wids_wid_wid_ops)-1 );  //get random key for random widget shortcode from $geah_rand_wids_wid_wid_ops
			
			$geah_rand_wids_wid_rand_widget_shortcode = $geah_rand_wids_wid_wid_ops[$geah_rand_wids_wid_wid_ops_key]; #gets shortcode for the selected widget from the list

			$text = empty( $geah_rand_wids_wid_rand_widget_shortcode ) ? '' : do_shortcode( $geah_rand_wids_wid_rand_widget_shortcode );
			*/

			echo('
				<script language="javascript">
				alert("WIP");


				</script>
				')

			// outputs the content of the widget
			?>
			<div class="textwidget"><?php echo !empty( $instance['filter'] ) ? wpautop( $text ) : $text; ?></div> 
			<?php 
		}

	/**
	 * Outputs the options form on admin
	 *
	 * @param array $instance The widget options
	 */
	public function form( $instance ) {
		// outputs the options form on admin
	}

	/**
	 * Processing widget options on save
	 *
	 * @param array $new_instance The new options
	 * @param array $old_instance The previous options
	 */
	public function update( $new_instance, $old_instance ) {
		// processes widget options to be saved
	}
}

add_action( 'widgets_init', function(){
	register_widget( 'RandWidsWid' );
});




  
///end
}

run_Random_Widgets_Widget();

