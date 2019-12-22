<?php

/**
 * The plugin bootstrap file
 *
 * This file is read by WordPress to generate the plugin information in the plugin
 * Dashboard. This file also includes all of the dependencies used by the plugin,
 * registers the activation and deactivation functions, and defines a function
 * this starts the plugin.
 *
 * @link:       http://class-advisor.com/wp-content/plugins/schools-directory-widget
 * @since             1.0.0
 * @package           Schools_Directory_Widget
 *
 * @wordpress-plugin
 * Plugin Name:       Schools directory widget
 * Plugin URI:        http://class-advisor.com/wp-content/plugins/schools-directory-widget
 * Description:        
 * Version:           1.0.0
 * Author:            samiam567
 * Author URI:        http://class-advisor.com
 * License:            GPL-2.0+
 * License URI:        http://www.gnu.org/licenses/gpl-2.0.txt
 * Text Domain:            schools-directory-widget
 * Domain Path:            /languages
 */

// If this file is called directly, abort.
if ( ! defined( 'WPINC' ) ) {
	die;
}

/**
 * The code that runs during plugin activation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-schools-directory-widget-activator.php';

/**
 * The code that runs during plugin deactivation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-schools-directory-widget-deactivator.php';

/** This action is documented in includes/class-schools-directory-widget-activator.php */
register_activation_hook( __FILE__, array( 'Schools_Directory_Widget_Activator', 'activate' ) );

/** This action is documented in includes/class-schools-directory-widget-deactivator.php */
register_activation_hook( __FILE__, array( 'Schools_Directory_Widget_Deactivator', 'deactivate' ) );

/**
 * The core plugin class that is used to define internationalization,
 * dashboard-specific hooks, and public-facing site hooks.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-schools-directory-widget.php';

/**
 * Begins execution of the plugin.
 *
 * Since everything within the plugin is registered via hooks,
 * then kicking off the plugin from this point in the file does
 * not affect the page life cycle.
 *
 * @since    1.0.0
 */
function run_Schools_Directory_Widget() {

	$plugin = new Schools_Directory_Widget();
	$plugin->run();
///Start

class My_Widget extends WP_Widget {

	/**
	 * Sets up the widgets name etc
	 */
	public function __construct() {
		$widget_ops = array( 
			'classname' => 'Custom display schools hierarchy',
			'description' => 'My Widget is awesome',
			
		);

		parent::__construct( 'my_widget', 'My Widget', $widget_ops );
	}

	/**
	 * Outputs the content of the widget
	 *
	 * @param array $args
	 * @param array $instance
	 * 
	 */
	public function widget( $args, $instance ) {
		// outputs the content of the widget

			//get the list of schools so we can check whether the user's answer for the schools field was valid
			$schools = get_page_by_title('Schools'); 
			$schools_page_id = $schools->ID;
			$page = get_post($schools_page_id,'ARRAY_A');
			$schools_list = $page['post_content'];

		//get the user's answer for the schools field from  bbpress
		$bp_school = bp_get_profile_field_data('field=School&user_id='.bp_loggedin_user_id());

		if ( ($bp_school != '') AND (strstr($schools_list, $bp_school) !== false) ) {  #check whether the user's answer for the schools field was valid
			$title = 'Your School Directory';   #The widget title
			echo $args['before_widget'];
			if (! empty($title)) {
				echo $args['before_title'] . $title . $args['after_title'];
			}
			//// Content goes here
			
		#	echo "Hello Dolly";
			if (is_user_logged_in()) {


				$user_school = get_page_by_title($bp_school);
				$user_school_id = $user_school->ID;


				$pg_disp_args = array(
			        'depth'        => 0,
			        'show_date'    => '',
			        'date_format'  => get_option( 'date_format' ),
			        'child_of'     => $user_school_id,
			        'exclude'      => '',
			        'title_li'     => '',
			        'echo'         => 0,
			        'authors'      => '',
			        'sort_column'  => '',
			        'link_before'  => '',
			        'link_after'   => ',',
			        'item_spacing' => 'preserve',
			        'walker'       => '',
					);
				$subuser_pages = wp_list_pages($pg_disp_args);
				#var_dump($subuser_pages);

				//processing $subuser_pages (currently a string) and turning it into a list
				$subuser_pages_array = array();
				$word = '';
				for ($subuser_pages_key=0; $subuser_pages_key < strlen($subuser_pages); $subuser_pages_key++) { 
					$char = substr($subuser_pages,$subuser_pages_key,1); 
				
					if ($char == ',') {
					
						array_push($subuser_pages_array,$word);
						$word = '';
					} else {
						$word .= $char;

					}
				
					

					

				}
				#var_dump($subuser_pages_array);

				//create and display dropdown menu
				echo '<select id="school_directory" name="school directoy">';
				for ($class_key=0; $class_key < count($subuser_pages_array) ; $class_key++) { 
					$page_name = $subuser_pages_array[$class_key];
					$page = get_page_by_title($page_name);
					$page_id = $page->ID;
					$page_url = get_permalink($page_id);
					echo '<option class="link" href="' . $page_url .'">';
					echo $page_name;
					echo '</option>';
				}

				echo '</select>';
				echo '<script type="text/javascript">
 var urlmenu = document.getElementById( "school_directory" );
 urlmenu.onchange = function() {
      window.open( this.options[ this.selectedIndex ].href );
 };
</script>';



			



				echo $args['after_widget'];

				} else {
					echo $args['before_widget'];
					echo '<p>Watch out partner! Looks like you either put an invalid school into your profile settings or left it blank.</p><p>You can edit your profile by mousing over the top right of the screen and then clicking "edit my profile."</p><p>If you know that your school is correct, try reloading.</p><p>Note: This new feature of ClassAdvisor is curently under development</p>';
					echo 'School: ';
					if (strlen($bp_school) > 0) {
						echo $bp_school;
					} else {
						echo '-empty-';
					}

					echo $args['after_widget'];
				}
			} else {
				echo $args['before_widget'];
				echo 'Looks like you are not logged in. Log in or create a free account to get cool features like this personalized school directory!';
				echo $args['after_widget'];
			}
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
	register_widget( 'My_Widget' );
});


////End
}
run_Schools_Directory_Widget();
