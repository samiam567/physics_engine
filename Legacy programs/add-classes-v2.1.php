<?php

/**
 * The plugin bootstrap file
 *
 * This file is read by WordPress to generate the plugin information in the plugin
 * Dashboard. This file also includes all of the dependencies used by the plugin,
 * registers the activation and deactivation functions, and defines a function
 * this starts the plugin.
 *
 * @link:       http://class-advisor.com/wp-content/plugins/add-classes-v2
 * @since             1.0.0
 * @package           Add_Classes_V2
 *
 * @wordpress-plugin
 * Plugin Name:       Add Classes V2
 * Plugin URI:        http://class-advisor.com/wp-content/plugins/add-classes-v2
 * Description:        Adds ClassAdvisor Classes
 * Version:           1.10.50
 * Author:            samiam567
 * Author URI:        
 * License:            GPL-2.0+
 * License URI:        http://www.gnu.org/licenses/gpl-2.0.txt
 * Text Domain:            add-classes-v2
 * Domain Path:            /languages
 */

// If this file is called directly, abort.
if ( ! defined( 'WPINC' ) ) {
	die;
}

/**
 * The code that runs during plugin activation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes-v2-activator.php';

/**
 * The code that runs during plugin deactivation.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes-v2-deactivator.php';

/** This action is documented in includes/class-add-classes-v2-activator.php */
register_activation_hook( __FILE__, array( 'Add_Classes_V2_Activator', 'activate' ) );

/** This action is documented in includes/class-add-classes-v2-deactivator.php */
register_activation_hook( __FILE__, array( 'Add_Classes_V2_Deactivator', 'deactivate' ) );

/**
 * The core plugin class that is used to define internationalization,
 * dashboard-specific hooks, and public-facing site hooks.
 */
require_once plugin_dir_path( __FILE__ ) . 'includes/class-add-classes-v2.php';

/**
 * Begins execution of the plugin.
 *
 * Since everything within the plugin is registered via hooks,
 * then kicking off the plugin from this point in the file does
 * not affect the page life cycle.
 *
 * @since    1.0.0
 */
function run_Add_Classes_V2() {

	$plugin = new Add_Classes_V2();
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
		'moderate_comments',
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
    $geah_acdp_retuns = geah_add_classes_data_processor($geah_add_classes_input,$rw_visibility_exclude);
    $done = $geah_acdp_retuns[0];
    $rw_visibility_exclude = $geah_acdp_retuns[1];
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
function geah_add_classes_data_processor($geah_add_classes_input,$rw_visibility_exclude) {
	
		echo($geah_add_classes_input);
	

	if ($geah_add_classes_input == ',, ') {
		echo 'Please type into the box the classes you would like to add. code: #existing_parent,class,class,class,class,%parentToCreate,class,class,...    Note: if the page is on the top level put #top.         .';
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
	$link_pairs = array();
	$delete = 'false';
	$pages_to_delete = array();
	for ($char_key = 0; $char_key  <=  strlen($geah_add_classes_input); $char_key++ ) {
		$char = substr($geah_add_classes_input,$char_key,1);

		#debug mode ('True' or 'False')
		$geah_debug_mode_add_classes = 'False';

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
					$parent = $word; 
					$word = '';
					$word_type = '';
					$publish = 'False';

					if ($geah_debug_mode_add_classes=='True') {
						echo 'parent';
					}

					break;

				case 'nonexisting_parent':
					$publish = 'True';
					$new_parent = $word;
					$title = $word;
					$comment_status = 'closed';
					$geah_rw_include = 'False';
					$word = '';

					if ($geah_debug_mode_add_classes=='True') {
						echo 'nonexisting_parent';
					}
					break;
				case 'delete':
					$delete = 'True';
					$title = $word;
					$word = '';

					if ($geah_debug_mode_add_classes == 'True') {
						echo 'Delete';
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
					$geah_rw_include = 'True';
					break;
			}
			$char = '';
			
			
		}
		
		
		if ($char == '#') {
			$word_type = 'parent';
			$char = '';
		}elseif ($char == '%') {
			$word_type = 'nonexisting_parent';
			$char = '';
	#	}elseif ($char == '-') {
	#		$word_type = 'delete';
	#		$char = '';

			if ($geah_debug_mode_add_classes == 'True') {
				echo 'wt=delete';
			}
		}

		
		$word .= $char;
		if ($publish == 'True' AND $title != '') {

			if ($geah_debug_mode_add_classes == 'True') {
				echo '  parent:';
				echo $parent;
				echo ' title:';
				echo $title;
				echo '   posting...   ';
			}

			if ($parent == 'top') {
				$parent_id = '';
				
			}
			else{
		
					$parent_page = get_page_by_title($parent); 
					$parent_id = $parent_page->ID;
			}

			if ($geah_debug_mode_add_classes=='True') {
				echo ' parent_id:';
				echo $parent_id;
				echo ' comment_status:';
				echo $comment_status;
			}


			$geah_add_class_parameters = array(
		        'ID' => 0,
		        'post_author' => 'ClassAdvisorAutoBot',
		        'post_date' => '',
		        'post_content' => '',
		        'post_content_filtered' => '',
		        'post_title' => $title,
		        'post_excerpt' => '',
		        'post_status' => 'publish',
		        'post_type' => 'page',
		        'comment_status' => $comment_status,
		        'rw_include_post' => $comment_status,
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

			#get the id of the newly created page
			$new_page = get_page_by_title($title); 
			$new_page_id = $new_page->ID;



		


			#disabling rating widget on page
			if ($geah_rw_include == 'False') {
				array_push($rw_visibility_exclude, $new_page_id);
				echo '  $rw_visibility_exclude:';
				var_dump ($rw_visibility_exclude);
			}


			if ($geah_debug_mode_add_classes = 'True'){
				echo(" Parent:");
				echo $parent;
			}

			if ($parent_id != '' AND $parent != 'top') {
				

				$new_page_url = get_permalink($new_page_id);
				$mode = 'add_link';

				array_push($link_pairs, $parent_id);
				array_push($link_pairs, $new_page_url);
				array_push($link_pairs, $title);
				array_push($link_pairs,'empty');
				array_push($link_pairs, $parent);
				array_push($link_pairs,$mode);

				if ($geah_debug_mode_add_classes =='True') {
					echo " new_page_url:";
					echo $new_page_url;
					echo " link_pairs:";
					var_dump($link_pairs);
				}
			}


			$done = True;
			$word_type = '';
			$word = '';
			$comment_status = 'open';
			$publish = 'False';

			if ($geah_debug_mode_add_classes=='True') {
				echo "published";
			}


		}

		if ($delete == 'True') {
			$mode = 'delete_link';
			array_push($link_pairs, $parent_id);
			array_push($link_pairs, $new_page_url);
			array_push($link_pairs, $title);
			array_push($link_pairs,'empty');
			array_push($link_pairs, $parent);
			array_push($link_pairs,$mode);
			array_push($pages_to_delete,$title);

			if ($geah_debug_mode_add_classes == 'True') {
				echo '  Title to delete';
				echo $title;
			}


			if ($geah_debug_mode_add_classes =='True') { 
				echo '  mode:';
				echo $mode;
			}
		}
	
	}
	#Update the parent page with all of the links to the child classes
	
	if (count($link_pairs) > 0) {
		for ($link_pair_key=0; $link_pair_key <= count($link_pairs); $link_pair_key+=6) { 
			
			$links = '';
			$parent_id = $link_pairs[$link_pair_key];
			$page_url = $link_pairs[($link_pair_key+1)];
			$title = $link_pairs[($link_pair_key+2)];
		#	$comment_status = $link_pairs[($link_pair_key+3)];
			$parent_title = $link_pairs[($link_pair_key+4)];
			$mode = $link_pairs[($link_pair_key+5)];
			
			$page = get_post($parent_id,'ARRAY_A');
			$updated_parent_parent_id = $page['post_parent'];

			$old_content = $page['post_content'];

		

			if ($mode == 'add_link') {
				$add_this = "<p><a href='";
				$add_this .= $page_url;
				$add_this .= "'/>";
				$add_this .= $title;
				$add_this .= "&nbsp;";
			#	$add_this .= "<p>,</p>";

				$new_content = $old_content;
				$new_content .= $add_this;
			/*
			}elseif ($mode == 'delete_link') {
				$delete_this = "<a href='";
				$delete_this .= $page_url;
				$delete_this .= "'>";
				$delete_this .= $title;
				$delete_this .= "</a>";
				$delete_this .= "<p></p>";
				$new_content = str_replace($delete_this, $old_content,'');
				*/
			}else{
	#			$done = False;
				echo ('link replace; not add_link or delete_link');
			}




			if ($geah_debug_mode_add_classes == 'True'){ 
				echo "          ";
				echo "  PAGE:";
				var_dump($page);
				echo "          ";
				echo  " Post-content:";
				echo $old_content;
				echo " link pair key";
				echo $link_pair_key;
				echo " newtitle:";
				echo $title;
				echo " Updated_parent_parent_id:";
				echo $updated_parent_parent_id;

				echo "  NEW_CONTENT";
				echo $new_content;
			}

		
			$comment_status = get_page_by_title($parent_title)->comment_status;
						

			
			

			# Updates the PARENT page
			$page = array(
				'ID' => (get_page_by_title($parent_title)->ID),
			    'post_author' => 'ClassAdvisorAutoBot',
			     'post_date' => '',
			        'post_content' => $new_content,
			        'post_content_filtered' => '',
			        'post_title' => $parent_title,
			        'post_excerpt' => '',
			        'post_status' => 'publish',
			        'post_type' => 'page',
			        'comment_status' => $comment_status,
			  #      'rw_include_post' => 1,
			        'ping_status' => '',
			        'post_password' => '',
			        'to_ping' =>  '',
			        'pinged' => '',
			        'post_parent' => $updated_parent_parent_id,
			        'menu_order' => 0,
			        'guid' => '',
			        'import_id' => 0,
			        'context' => '',
				);


			wp_update_post($page);
		}
	}
	
	#Delete Page thing
	if ($delete == 'True') {
		for ($delete_page_key=0; $delete_page_key < count($pages_to_delete); $delete_page_key++) { 
			


			$delete_page_id = get_page_by_title($pages_to_delete[$delete_page_key])->ID;

			if ($geah_debug_mode_add_classes == 'True') {
				echo "  deleting:";
				echo $delete_page_id;

			}
			wp_delete_post($delete_page_id);
			$delete = 'False';
		}
	}
	$geah_acdp_retuns = [$done,$rw_visibility_exclude];
	return $geah_acdp_retuns;
}



/* Stop coding here */
}
run_Add_Classes_V2();
?>