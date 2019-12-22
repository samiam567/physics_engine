<?php

/**
 * The plugin bootstrap file
 *
 * This file is read by WordPress to generate the plugin information in the plugin
 * Dashboard. This file also includes all of the dependencies used by the plugin,
 * registers the activation and deactivation functions, and defines a function
 * this starts the plugin.
 *
 * @link:       http://class-advisor.com/wp-admin/add-classes
 * @since             1.0.0
 * @package           Add_Classes
 *
 * @wordpress-plugin
 * Plugin Name:       Add Classes
 * Plugin URI:        http://class-advisor.com/wp-admin/add-classes
 * Description:        Adds ClassAdvisor Classes
 * Version:           1.0.0
 * Author:            samiam567
 * Author URI:        
 * License:            GPL-2.0+
 * License URI:        http://www.gnu.org/licenses/gpl-2.0.txt
 * Text Domain:            add-classes
 * Domain Path:            /languages
 */

// If this file is called directly, abort.
if ( ! defined( 'WPINC' ) ) {
	die;
}

/**
 * The code that runs during plugin activation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes-activator.php';

/**
 * The code that runs during plugin deactivation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes-deactivator.php';

/** This action is documented in includes/class-add-classes-activator.php */
register_activation_hook( __FILE__, array( 'Add_Classes_Activator', 'activate' ) );

/** This action is documented in includes/class-add-classes-deactivator.php */
register_activation_hook( __FILE__, array( 'Add_Classes_Deactivator', 'deactivate' ) );

/**
 * The core plugin class that is used to define internationalization,
 * dashboard-specific hooks, and public-facing site hooks.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes.php';

/**
 * Begins execution of the plugin.
 *
 * Since everything within the plugin is registered via hooks,
 * then kicking off the plugin from this point in the file does
 * not affect the page life cycle.
 *
 * @since    1.0.0
 */


/*debug mode*/
global $geah_debug_mode_add_classes;
$geah_debug_mode_add_classes = 'False'; /* 'True' or 'False' */


function run_Add_Classes() {
	$plugin = new Add_Classes();
	$plugin->run();
/**
*Start Coding here
*/

/* Create add classes page (settings page) */
function geah_add_submenu_page_for_add_classes() {
	add_submenu_page(
		'edit.php?post_type=page',
		'Add Classes',
		'Add Classes...',
		'publish_pages',
		'add_classes',
		'geah_add_classes_page'
		);

}
add_action('admin_menu','geah_add_submenu_page_for_add_classes');


/* Render add class page */
function geah_add_classes_page() {
	$geah_add_classes_input = '';
    $geah_add_classes_input = $_POST['value'];
    $geah_add_classes_input .= ',, ';
    $done = geah_add_classes_data_processor($geah_add_classes_input);
    if ($done) {
    	echo 'class creation successful';
    }else{
    	echo "class creation failed";
    }

	?>
	<!--  Create the form $ reload  -->
	<form method="post" action="">
	<input type="text" name="value">
	<input type="submit">
	</form>

<?php 
}

/* Processing class data funtion */
function geah_add_classes_data_processor($geah_add_classes_input) {
	
	if ($geah_debug_mode_add_classes=='True') {
		echo($geah_add_classes_input);
	}

	if ($geah_add_classes_input == ',, ') {
		echo 'Please type into the box the classes you would like to add. code: #existing_parent,class,class,class,class,%parentToCreate,class,class ...    Note: if the page is on the top level put #top.';
	}
	$word = '';
	$char = '';
	$parent = '';
	$title = '';
	$word_type = '';
	$publish = 'False';
	$error = False;
	$done = False;
	$comment_status = 'open';
	for ($char_key = 0; $char_key  <=  strlen($geah_add_classes_input); $char_key++ ) {
		$char = substr($geah_add_classes_input,$char_key,1);

		if ($geah_debug_mode_add_classes=='True') {
			echo "loop;";
			echo $char;
		}
		
		
		if (strlen($new_parent) != 0) {
			$parent = $new_parent;
			$new_parent = '';
		}

		if ($char == ",") {
			switch ($word_type) {
				case 'parent':
					$parent = $word; /* $word; */
					$word = '';
					break;

				case 'nonexisting_parent':
					$publish = 'True';
					$new_parent = $word;
					$title = $word;
					$comment_status = 'closed';
					$word = '';

					if ($geah_debug_mode_add_classes=='True') {
						echo 'nonexisting_parent';
					}
					break;

				default:
					if ($geah_debug_mode_add_classes=='True') {
						echo "wt=class";
						echo $word;
					}

					$publish = 'True';
					$title = $word;
					$word_type = 'class';
					$comment_status = 'open';
					
					break;
			}
			$char = '';
			
			
		}

		/*if ($word_type == '') {
			$word = '';
		} */
		if ($char == '#') {
			$word_type = 'parent';
			$char = '';
		}
		if ($char == '%') {
			$word_type = 'nonexisting_parent';
			$char = '';
		}

		
		$word .= $char;
		if ($publish == 'True' AND $title != '') {

			if ($geah_debug_mode_add_classes=='True') {
				echo '  parent:';
				echo $parent;
				echo ' title:';
				echo $title;
				echo '   posting...   ';
			}

			if ($parent == 'top') {
				$parent_id = '';
			}

			switch ($parent) {
				case 'schools':
					$parent_id = 11;

					break;
				
				default:
					$parent_page = get_page_by_title($parent); 
					$parent_id = $parent_page->ID;
					break;
			}

			if ($geah_debug_mode_add_classes=='True') {
				echo ' parent_id:';
				echo $parent_id;
			}


			$geah_add_class_parameters = array(
		        'ID' => 0,
		        'post_author' => '',
		        'post_date' => '',
		        'post_content' => ' ',
		        'post_content_filtered' => '',
		        'post_title' => $title,
		        'post_excerpt' => '',
		        'post_status' => 'publish',
		        'post_type' => 'page',
		        'comment_status' => $comment_status,
		        'ping_status' => '',
		        'post_password' => '',
		        'to_ping' =>  '',
		        'pinged' => '',
		        'post_parent' => $parent_id,
		        'menu_order' => 0,
		        'guid' => '',
		        'import_id' => 0,
		        'context' => '',
		    );
			$postar = $geah_add_class_parameters;
			wp_insert_post($postar,$wp_error = false);

			$done = True;
			$word_type = '';
			$word = '';
			$comment_status = 'open';
			$publish = 'False';

			if ($geah_debug_mode_add_classes=='True') {
				echo "published";
			}


		}
	
	}
	return $done;
}





/** Stop coding here */
}
run_Add_Classes();

